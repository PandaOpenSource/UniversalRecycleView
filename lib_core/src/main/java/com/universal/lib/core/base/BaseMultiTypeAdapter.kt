package com.universal.lib.core.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.INVALID_TYPE
import com.universal.lib.core.base.model.ItemViewTypeSupplier
import com.universal.lib.core.base.viewholder.BaseViewHolder
import com.universal.lib.core.databinding.DefaultRecyclerviewItemViewEmptyBinding
import com.universal.lib.core.viewbinding.ViewHolderBindingExt

/**
 * 多类型布局Adapter
 *
 *
 * 数据操作示例:
 * ```kotlin
 *       val adapter: BaseMultiTypeAdapter
 *       // 替换/设置数据 同replaceAll
 *       adapter.dataList.data = listOf('1', '2')
 *       // 添加数据
 *       adapter.dataList.addAll(listOf('3'. '4'))
 *       // 删除数据
 *       adapter.dataList.clear()
 *       // 替换/设置数据
 *       adapter.dataList.replaceAll(listOf('1', '2'))
 * ```
 *
 * Created by jixiongxu on 2022/5/13.
 * Copyright (c) 2022 UniversalRecycleview. All rights reserved.
 *
 * @author jixiongxu
 * @since 2022/5/13
 */
open class BaseMultiTypeAdapter<DATA> : RecyclerView.Adapter<BaseViewHolder<DATA>>() {
    /**
     * 数据集合: 定义了数据的设置/添加/删除等操作方法
     */
    val dataList: AdapterDataList<DATA> = object : AdapterDataListImpl<DATA>() {
        override val adapter: RecyclerView.Adapter<*>
            get() = this@BaseMultiTypeAdapter
    }

    val registry: AdapterRegistry<DATA> = AdapterRegistry()

    var viewHolderFactory: ViewHolderFactory<BaseViewHolder<DATA>>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DATA> {
        var viewHolder: BaseViewHolder<DATA>? = registry.createViewHolder(parent, viewType)
        if (viewHolder != null) {
            viewHolder.adapter = this
            return viewHolder
        }
        viewHolder = viewHolderFactory?.onCreateViewHolder(parent, viewType)
        if (viewHolder != null) {
            viewHolder.adapter = this
            return viewHolder
        }
        val binding = ViewHolderBindingExt.inflate(
            LayoutInflater.from(parent.context),
            parent,
            attachToParent = false,
            inflate = DefaultRecyclerviewItemViewEmptyBinding::inflate
        )
        @Suppress("UNREACHABLE_CODE")
        return BaseViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DATA>, position: Int) {
        holder.onBind(getItemData(position)!!, position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    open fun getItemData(position: Int): DATA? {
        return dataList.get(position)
    }

    override fun getItemViewType(position: Int): Int {
        val data = getItemData(position) ?: return super.getItemViewType(position)
        var viewType: Int
        if (data is ItemViewTypeSupplier) {
            viewType = data.itemViewType
            if (viewType != INVALID_TYPE) {
                return viewType
            }
        }
        viewType = registry.getItemViewType(data)
        if (viewType != INVALID_TYPE) {
            return viewType
        }
        return super.getItemViewType(position)
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<DATA>) {
        super.onViewAttachedToWindow(holder)
        holder.onViewAttachedToWindow()
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<DATA>) {
        super.onViewDetachedFromWindow(holder)
        holder.onViewDetachedFromWindow()
    }

}


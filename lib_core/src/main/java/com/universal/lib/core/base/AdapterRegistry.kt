package com.universal.lib.core.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.universal.lib.core.base.model.DataModel
import com.universal.lib.core.base.model.ItemViewTypeSupplier
import com.universal.lib.core.base.viewholder.BaseViewHolder

class AdapterRegistry<DATA> {
    val layouts: MutableMap<Int, ViewHolderSupplier<DATA>> = mutableMapOf()
    val dataViewTypes: MutableMap<Class<*>, Int> = mutableMapOf()

    /**
     * 给数据类注册指定的viewType
     */
    fun registerViewType(viewType: Int, dataClass: Class<*>) {
        if (viewType == RecyclerView.INVALID_TYPE) {
            return
        }
        dataViewTypes[dataClass] = viewType
    }

    /**
     * 注册布局文件及相关ViewHolder的创建方法, 将布局LayoutResId作为 viewType
     */
    fun registerLayout(
        @LayoutRes layoutResId: Int,
        supplier: ViewHolderSupplier<DATA>,
        dataClass: Class<*>? = null,
    ) {
        if (layoutResId == 0) return
        layouts[layoutResId] = supplier
        dataClass?.let {
            registerViewType(layoutResId, dataClass)
        }
    }

    /**
     * 注册布局文件及相关ViewHolder的创建方法, 将布局LayoutResId作为 viewType
     */
    fun registerLayout(
        @LayoutRes layoutResId: Int,
        onCreate: ((parent: ViewGroup, viewType: Int) -> BaseViewHolder<DATA>),
        onBind: ViewHolderOnBindFunction<DATA>,
        dataClass: Class<*>? = null,
    ) {
        if (layoutResId == 0) return
        val supplier = ViewHolderSupplierImpl(onCreate = onCreate, onBind = onBind)
        registerLayout(layoutResId, supplier, dataClass)
    }

    /**
     * 注册布局文件及相关ViewHolder的创建方法, 将布局LayoutResId作为 viewType
     */
    fun registerLayout(
        @LayoutRes layoutResId: Int,
        onBind: ViewHolderOnBindFunction<DATA>,
        dataClass: Class<*>? = null,
    ) {
        if (layoutResId == 0) return
        val supplier = ViewHolderSupplierImpl(onCreate = { parent: ViewGroup, _: Int ->
            BaseViewHolder(
                BaseViewHolder.inflateItemView(parent.context,
                layoutResId,
                parent))
        }, onBind = onBind)
        registerLayout(layoutResId, supplier, dataClass)
    }

    fun createViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DATA>? {
        var viewHolder: BaseViewHolder<DATA>? = null
        if (layouts.containsKey(viewType)) {
            val holderSupplier = layouts[viewType]
            viewHolder = holderSupplier?.onCreateViewHolder(parent, viewType)
            if (viewHolder != null) {
                viewHolder.viewHolderOnBind = holderSupplier?.newOnBind()
            }
        }
        return viewHolder
    }

    fun getItemViewType(data: Any): Int {
        var viewType = RecyclerView.INVALID_TYPE
        if (data is DataModel<*>) {
            viewType = data.itemViewType
            if (viewType == RecyclerView.INVALID_TYPE) {
                data.get<Any>()?.let {
                    viewType = dataViewTypes[it.javaClass] ?: RecyclerView.INVALID_TYPE
                }
            }
        } else if (data is ItemViewTypeSupplier) {
            viewType = data.itemViewType
        }
        if (viewType != RecyclerView.INVALID_TYPE) {
            return viewType
        }
        viewType = dataViewTypes[data.javaClass] ?: RecyclerView.INVALID_TYPE
        if (viewType != RecyclerView.INVALID_TYPE) {
            return viewType
        }
        return RecyclerView.INVALID_TYPE
    }
}
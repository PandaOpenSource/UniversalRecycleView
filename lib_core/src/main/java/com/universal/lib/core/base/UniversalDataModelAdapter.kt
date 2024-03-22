package com.universal.lib.core.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

import com.universal.lib.core.base.model.DataModel
import com.universal.lib.core.base.model.ItemViewTypeSupplier
import com.universal.lib.core.base.viewholder.BaseViewHolder
import com.universal.lib.core.viewbinding.ViewBindingInflateFunction


/**
 * 通用的数据类型 DataModel<T> 的Adapter
 *
 * Created by jixiongxu on 2022/5/13.
 * Copyright (c) 2022 UniversalRecycleview. All rights reserved.
 *
 * @author jixiongxu
 * @since 2022/5/13
 */
open class UniversalDataModelAdapter : BaseMultiTypeAdapter<DataModel<*>>() {

    fun setup(supplier: BaseLayoutViewHolderSupplier, dataClass: Class<*>? = null) {
        registry.registerLayout(supplier.layoutResId, supplier, dataClass)
    }
}

abstract class BaseLayoutViewHolderSupplier : ViewHolderSupplier<DataModel<*>>,
    ItemViewTypeSupplier {

    abstract val layoutResId: Int

    override val itemViewType: Int get() = layoutResId

    companion object {

        inline fun <reified VB : ViewBinding> createViewBindingViewHolder(
            parent: ViewGroup,
            inflate: ViewBindingInflateFunction<VB>,
        ): BaseViewHolder<DataModel<*>> {
            return BaseViewHolder.createBaseViewHolder(parent, inflate)
        }

        @JvmStatic
        fun inflateItemView(
            @LayoutRes
            layoutResId: Int,
            parent: ViewGroup,
        ): View {
            return BaseViewHolder.inflateItemView(parent.context, layoutResId, parent)
        }
    }
}


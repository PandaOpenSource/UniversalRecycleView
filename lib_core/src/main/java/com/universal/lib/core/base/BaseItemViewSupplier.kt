package com.universal.lib.core.base

import android.view.ViewGroup
import com.universal.lib.core.base.model.DataModel
import com.universal.lib.core.base.viewholder.BaseViewHolder

/**
 * @des
 * @author: xujixiong
 * @create:2024/3/4 16:55
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseItemViewSupplier<T> : BaseLayoutViewHolderSupplier(), ViewHolderOnBindFunction<DataModel<*>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DataModel<*>> {
        return BaseViewHolder(BaseViewHolder.inflateItemView(parent.context, layoutResId, parent))
    }

    final override fun newOnBind(): ViewHolderOnBindFunction<DataModel<*>>? {
        return javaClass.getConstructor().newInstance()
    }

    override fun onBind(holder: BaseViewHolder<DataModel<*>>, data: DataModel<*>, position: Int) {
        val item = data.get<Any>() as? T
        onItemBind(holder, item, position)
    }

    abstract fun onItemBind(holder: BaseViewHolder<DataModel<*>>, item: T?, position: Int)
}
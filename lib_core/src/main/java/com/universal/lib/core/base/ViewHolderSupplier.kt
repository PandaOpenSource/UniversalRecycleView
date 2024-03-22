package com.universal.lib.core.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.universal.lib.core.base.viewholder.BaseViewHolder

fun interface ViewHolderOnBindFunction<T> {
    fun onBind(holder: BaseViewHolder<T>, data: T, position: Int)
}

interface ViewHolderFactory<VH : ViewHolder> {
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH?
}

interface ViewHolderSupplier<DATA> : ViewHolderFactory<BaseViewHolder<DATA>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DATA>

    fun newOnBind(): ViewHolderOnBindFunction<DATA>?
}

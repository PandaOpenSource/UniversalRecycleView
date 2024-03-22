package com.universal.lib.core.base.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.universal.lib.core.base.ViewHolderOnBindFunction
import com.universal.lib.core.viewbinding.ViewBindingInflateFunction
import com.universal.lib.core.viewbinding.ViewHolderBindingExt


/**
 * Created by jixiongxu on 2022/5/13.
 * Copyright (c) 2022 UniversalRecycleview. All rights reserved.
 *
 * @author jixiongxu
 * @since 2022/5/13
 */
open class BaseViewHolder<DATA>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var viewHolderOnBind: ViewHolderOnBindFunction<DATA>? = null
    var adapter: RecyclerView.Adapter<*>? = null

    @CallSuper
    open fun onBind(data: DATA, position: Int) {
        viewHolderOnBind?.onBind(this, data, position)
    }

    open fun onViewAttachedToWindow() {}

    open fun onViewDetachedFromWindow() {}

    companion object {

        @JvmStatic
        fun inflateItemView(
            context: Context,
            @LayoutRes layoutResId: Int,
            parent: ViewGroup,
        ): View {
            return LayoutInflater.from(context).inflate(layoutResId, parent, false)
        }

        fun <VB : ViewBinding, DATA> createBaseViewHolder(
            parent: ViewGroup,
            inflate: ViewBindingInflateFunction<VB>,
        ): BaseViewHolder<DATA> {
            val binding = ViewHolderBindingExt.inflate(
                LayoutInflater.from(parent.context),
                parent,
                attachToParent = false,
                inflate = inflate
            )
            return BaseViewHolder(binding.root)
        }
    }
}


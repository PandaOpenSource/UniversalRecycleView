package com.universal.lib.core.viewbinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.universal.lib.core.R


/**
 * ViewHolder的ViewBinding扩展函数
 *
 * Created by jixiongxu on 2022/5/13.
 * Copyright (c) 2022 UniversalRecycleview. All rights reserved.
 *
 * @author jixiongxu
 * @since 2022/5/13
 */
object ViewHolderBindingExt {

    fun <T : ViewBinding> inflate(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        attachToParent: Boolean,
        inflate: ViewBindingInflateFunction<T>,
    ): T {
        return inflate.inflate(inflater, parent, attachToParent).also {
            it.root.setTag(R.id.recyclerview_item_view_binding, it)
        }
    }

    fun <T : ViewBinding> bind(
        rootView: View,
        bind: ViewBindingBindFunction<T>,
    ): T {
        return bind.bind(rootView)
    }
}

/**
 * 获取itemView的ViewBinding类
 * ```
 *   val root = withViewBinding(HiyaludoRecyclerviewItemViewEmptyBinding::bind).root
 * ```
 */
inline fun <reified T : ViewBinding> RecyclerView.ViewHolder.withViewBinding(bind: ViewBindingBindFunction<T>): T {
    val binding = itemView.getTag(R.id.recyclerview_item_view_binding)
    return if (binding is T) {
        binding
    } else ViewHolderBindingExt.bind(itemView, bind = bind).also {
        itemView.setTag(R.id.recyclerview_item_view_binding, it)
    }
}

fun <T : ViewBinding> RecyclerView.ViewHolder.setItemViewBinding(binding: T) {
    this.itemView.setTag(R.id.recyclerview_item_view_binding, binding)
}

inline fun <reified T : ViewBinding> RecyclerView.ViewHolder.getItemViewBinding(): T? {
    return this.itemView.getTag(R.id.recyclerview_item_view_binding) as? T
}
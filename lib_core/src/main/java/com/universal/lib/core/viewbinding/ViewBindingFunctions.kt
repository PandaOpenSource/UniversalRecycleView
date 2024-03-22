package com.universal.lib.core.viewbinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by jixiongxu on 2022/12/22
 */
fun interface ViewBindingInflateFunction<T> {
    fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): T
}

fun interface ViewBindingBindFunction<T> {
    fun bind(root: View): T
}
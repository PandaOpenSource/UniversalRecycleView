/**
 * Created by jixiongxu on 2023/2/15.
 * Copyright (c) 2023 UniversalRecycleview. All rights reserved.
 *
 * @author jixiongxu
 * @since 2023/2/15
 */
@file:Suppress("UNUSED")

package com.universal.lib.core

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.setListSpacingInsetVertical(spacing: Int) {
    this.addItemDecoration(ListSpacingInset.Vertical(spacing))
}

fun RecyclerView.setListSpacingInsetHorizontal(spacing: Int) {
    this.addItemDecoration(ListSpacingInset.Horizontal(spacing))
}

fun RecyclerView.setGridSpacingInset(spacing: Int, spanCount: Int) {
    this.addItemDecoration(ListSpacingInset.GridSpacingInset(spacing, spanCount))
}

fun RecyclerView.asLinearLayoutManagerVertical() {
    this.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
}

fun RecyclerView.asLinearLayoutManagerHorizontal() {
    this.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
}
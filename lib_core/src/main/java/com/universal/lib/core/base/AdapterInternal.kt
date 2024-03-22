package com.universal.lib.core.base

import android.view.ViewGroup
import com.universal.lib.core.base.viewholder.BaseViewHolder

/**
 * Created by jixiongxu on 2022/5/13.
 * Copyright (c) 2022 UniversalRecycleview. All rights reserved.
 *
 * @author jixiongxu
 * @since 2022/5/13
 */

internal class ViewHolderSupplierImpl<DATA>(
    val onCreate: ((parent: ViewGroup, viewType: Int) -> BaseViewHolder<DATA>),
    val onBind: ViewHolderOnBindFunction<DATA>?,
) : ViewHolderSupplier<DATA> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DATA> {
        return this.onCreate.invoke(parent, viewType)
    }

    override fun newOnBind(): ViewHolderOnBindFunction<DATA>? {
        return this.onBind
    }
}

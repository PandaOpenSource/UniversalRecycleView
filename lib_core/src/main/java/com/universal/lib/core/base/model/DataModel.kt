package com.universal.lib.core.base.model

import androidx.recyclerview.widget.RecyclerView.INVALID_TYPE

/**
 * 通用的Adapter数据Model
 *
 * Created by jixongxu on 2022/5/13.
 * Copyright (c) 2022 UniversalRecycleview. All rights reserved.
 *
 * @author jixongxu
 * @since 2022/5/13
 */
open class DataModel<T> : ItemViewTypeSupplier {

    var data: T? = null

    override var itemViewType: Int = INVALID_TYPE
        get() {
            if (field != INVALID_TYPE && field != 0) {
                return field
            }
            data?.let {
                if (it is ItemViewTypeSupplier) {
                    if (INVALID_TYPE != it.itemViewType) {
                        return it.itemViewType
                    }
                }
            }
            return field
        }

    fun set(value: T?) {
        data = value
    }

    /**
     * eg. `val data:String? = ItemDataModel.get<String>()`
     */
    inline fun <reified T> get(): T? {
        if (data is T) {
            return data as T
        }
        return null
    }

    /**
     * eg. `val data:String? = ItemDataModel.get(String::class.java)`
     */
    fun <T> get(clazz: Class<T>): T? {
        if (data != null && clazz.isInstance(data)) {
            return clazz.cast(data)
        }
        return null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DataModel<*>

        return data == other.data
    }

    override fun hashCode(): Int {
        return data?.hashCode() ?: 0
    }
}

interface ItemViewTypeSupplier {
    val itemViewType: Int
}
package com.universal.lib.core.base

/**
 * 提供方便操作Adapter数据集合的接口
 *
 * Created by jixiongxu on 2022/5/13.
 * Copyright (c) 2022 UniversalRecycleview. All rights reserved.
 *
 * @author jixiongxu
 * @since 2022/5/13
 */
abstract class AdapterDataList<E> {

    abstract var data: List<E>

    val size: Int get() = data.size

    fun isEmpty(): Boolean = data.isEmpty()

    fun get(index: Int): E = data[index]

    abstract fun clear()

    abstract fun add(element: E): Boolean

    abstract fun add(index: Int, element: E): Boolean

    abstract fun addAll(elements: Collection<E>): Boolean

    abstract fun addAll(index: Int, elements: Collection<E>): Boolean

    abstract fun replaceAll(elements: Collection<E>): Boolean

    abstract fun modify(action: (MutableList<E>) -> Boolean): Boolean

    abstract fun remove(element: E): Boolean

    abstract fun removeIf(action: (E) -> Boolean): Boolean

    abstract fun updateIf(action: (E) -> Boolean): Boolean
}
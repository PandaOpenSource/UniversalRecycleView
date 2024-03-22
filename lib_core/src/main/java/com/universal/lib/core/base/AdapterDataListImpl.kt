package com.universal.lib.core.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView

internal abstract class AdapterDataListImpl<DATA> : AdapterDataList<DATA>() {
    abstract val adapter: RecyclerView.Adapter<*>

    private var items: MutableList<DATA> = mutableListOf()

    override var data: List<DATA>
        get() {
            return items
        }
        set(value) {
            items = if (value.isEmpty()) {
                mutableListOf()
            } else {
                value.toMutableList()
            }
            notifyDataSetChanged()
        }

    @SuppressLint("NotifyDataSetChanged")
    private fun notifyDataSetChanged() {
        adapter.notifyDataSetChanged()
    }

    override fun clear() {
        if (items.isNotEmpty()) {
            items.clear()
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun addAll(elements: Collection<DATA>): Boolean {
        if (elements.isEmpty()) {
            return true
        }
        val positionStart = items.size
        return items.addAll(elements).also {
            adapter.notifyItemRangeInserted(positionStart, elements.size)
        }
    }

    override fun addAll(index: Int, elements: Collection<DATA>): Boolean {
        if (elements.isEmpty()) {
            return true
        }
        return items.addAll(index, elements).also {
            adapter.notifyItemRangeInserted(index, elements.size)
        }
    }

    override fun replaceAll(elements: Collection<DATA>): Boolean {
        if (elements.isEmpty()) {
            clear()
        } else {
            items.clear()
            items.addAll(elements)
            notifyDataSetChanged()
        }
        return true
    }

    override fun modify(action: (MutableList<DATA>) -> Boolean): Boolean {
        if (action.invoke(items)) {
            notifyDataSetChanged()
        }
        return true
    }

    override fun add(element: DATA): Boolean {
        return addAll(listOf(element))
    }

    override fun add(index: Int, element: DATA): Boolean {
        return addAll(index, listOf(element))
    }

    override fun remove(element: DATA): Boolean {
        val index = items.indexOf(element)
        if (index >= 0) {
            items.remove(element)
            adapter.notifyItemRemoved(index)
            return true
        }
        return false
    }

    override fun removeIf(action: (DATA) -> Boolean): Boolean {
        val removes: MutableList<DATA> = ArrayList()
        items.forEach { model ->
            if (action.invoke(model)) {
                removes.add(model)
            }
        }
        val iterator = removes.iterator()
        var removed = false
        while (iterator.hasNext()) {
            val next = iterator.next()
            val res = remove(next)
            if (res) {
                removed = true
            }
        }
        return removed
    }

    override fun updateIf(action: (DATA) -> Boolean): Boolean {
        for (index in data.indices) {
            if (action.invoke(data[index])) {
                adapter.notifyItemChanged(index)
            }
        }
        return false
    }
}
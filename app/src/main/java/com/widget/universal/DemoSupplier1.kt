package com.widget.universal

import com.universal.lib.core.base.BaseItemViewSupplier
import com.universal.lib.core.base.model.DataModel
import com.universal.lib.core.base.viewholder.BaseViewHolder
import com.universal.lib.core.viewbinding.withViewBinding
import com.widget.universal.databinding.DemoSupplier1Binding

/**
 * @des
 * @author: xujixiong
 * @create:2024/3/4 16:50
 */
class DemoSupplier1 : BaseItemViewSupplier<String>() {

    companion object {
        val default = DemoSupplier1()
    }

    override fun onItemBind(holder: BaseViewHolder<DataModel<*>>, item: String?, position: Int) {
        val binding = holder.withViewBinding(DemoSupplier1Binding::bind)
        binding.text.text = item
    }

    override val layoutResId: Int
        get() = R.layout.demo_supplier_1

}
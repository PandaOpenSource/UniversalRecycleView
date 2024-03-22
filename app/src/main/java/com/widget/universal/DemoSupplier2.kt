package com.widget.universal

import android.annotation.SuppressLint
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
class DemoSupplier2 : BaseItemViewSupplier<ModelOfSupplier2>() {

    companion object {
        val default = DemoSupplier2()
    }

    @SuppressLint("SetTextI18n")
    override fun onItemBind(holder: BaseViewHolder<DataModel<*>>, item: ModelOfSupplier2?, position: Int) {
        val binding = holder.withViewBinding(DemoSupplier1Binding::bind)
        binding.text.text = "name:${item?.name},age:${item?.age}"
    }

    override val layoutResId: Int
        get() = R.layout.demo_supplier_2

}
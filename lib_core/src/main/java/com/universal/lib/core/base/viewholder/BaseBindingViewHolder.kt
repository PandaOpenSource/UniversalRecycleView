package com.universal.lib.core.base.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.universal.lib.core.viewbinding.ViewBindingInflateFunction
import com.universal.lib.core.viewbinding.setItemViewBinding


/**
 * Created by jixiongxu on 2022/12/22
 */
abstract class BaseBindingViewHolder<DATA, BINDING : ViewBinding>(itemView: View) :
    BaseViewHolder<DATA>(itemView) {

    abstract val binding: BINDING?

    companion object {

        inline fun <reified BINDING : ViewBinding, VH : ViewHolder> createViewHolder(
            context: Context,
            parent: ViewGroup?,
            inflate: ViewBindingInflateFunction<BINDING>,
            viewHolderClass: Class<VH>
        ): VH {
            val binding = inflate.inflate(LayoutInflater.from(context), parent, false)
            val holder = viewHolderClass.getConstructor(View::class.java).newInstance(binding.root)
            holder.setItemViewBinding(binding)
            return holder
        }
    }
}


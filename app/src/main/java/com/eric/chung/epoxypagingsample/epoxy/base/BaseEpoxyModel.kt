package com.eric.chung.epoxypagingsample.epoxy.base

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.EpoxyModelWithHolder

abstract class BaseEpoxyModel<in T : ViewDataBinding> : EpoxyModelWithHolder<ViewBindingHolder>() {
    abstract fun T.bind()

    @Suppress("UNCHECKED_CAST")
    override fun bind(holder: ViewBindingHolder) {
        (holder.dataBinding as T).bind()
    }

    override fun createNewHolder(): ViewBindingHolder {
        return ViewBindingHolder(this::class.java)
    }
}
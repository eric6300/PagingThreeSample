package com.eric.chung.epoxypagingsample.epoxy.model

import com.airbnb.epoxy.EpoxyModelClass
import com.eric.chung.epoxypagingsample.R
import com.eric.chung.epoxypagingsample.databinding.ModelLoadingBinding
import com.eric.chung.epoxypagingsample.epoxy.base.BaseEpoxyModel

@EpoxyModelClass(layout = R.layout.model_loading)
abstract class LoadingModel : BaseEpoxyModel<ModelLoadingBinding>() {
    override fun ModelLoadingBinding.bind() {}
}
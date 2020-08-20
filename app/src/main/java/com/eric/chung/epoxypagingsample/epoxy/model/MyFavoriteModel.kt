package com.eric.chung.epoxypagingsample.epoxy.model

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.eric.chung.epoxypagingsample.R
import com.eric.chung.epoxypagingsample.data.Movie
import com.eric.chung.epoxypagingsample.databinding.ModelMyFavoriteBinding
import com.eric.chung.epoxypagingsample.epoxy.base.BaseEpoxyModel
import com.eric.chung.epoxypagingsample.util.ImageLoader

@EpoxyModelClass(layout = R.layout.model_my_favorite)
abstract class MyFavoriteModel : BaseEpoxyModel<ModelMyFavoriteBinding>() {

    private val baseUrl = "http://image.tmdb.org/t/p/w185/"

    @EpoxyAttribute
    lateinit var movie: Movie

    @EpoxyAttribute
    lateinit var imageLoader: ImageLoader

    override fun ModelMyFavoriteBinding.bind() {
        tvTitle.text = movie.title
        imageLoader.load(baseUrl + movie.posterPath, ivThumbnail)
    }
}
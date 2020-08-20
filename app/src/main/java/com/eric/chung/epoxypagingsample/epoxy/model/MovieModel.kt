package com.eric.chung.epoxypagingsample.epoxy.model

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.eric.chung.epoxypagingsample.R
import com.eric.chung.epoxypagingsample.data.Movie
import com.eric.chung.epoxypagingsample.databinding.ModelMovieBinding
import com.eric.chung.epoxypagingsample.epoxy.base.BaseEpoxyModel
import com.eric.chung.epoxypagingsample.util.ImageLoader

@EpoxyModelClass(layout = R.layout.model_movie)
abstract class MovieModel : BaseEpoxyModel<ModelMovieBinding>() {

    private val baseUrl = "http://image.tmdb.org/t/p/w185/"

    @EpoxyAttribute
    lateinit var imageLoader: ImageLoader

    @EpoxyAttribute
    lateinit var movie: Movie

    @EpoxyAttribute
    lateinit var description: String

    override fun ModelMovieBinding.bind() {
        tvTitle.text = movie.title
        tvDescription.text = movie.overview
        tvVoteAverage.text = "⭐️ ${movie.voteAverage}"
        imageLoader.load(baseUrl + movie.posterPath, ivThumbnail)
    }
}

package com.eric.chung.epoxypagingsample.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eric.chung.epoxypagingsample.R
import com.eric.chung.epoxypagingsample.data.Movie
import com.eric.chung.epoxypagingsample.databinding.ModelMovieBinding
import com.eric.chung.epoxypagingsample.util.ImageLoader

class MovieViewHolder(view: View, private val imageLoader: ImageLoader) : RecyclerView.ViewHolder(view) {

    private val baseUrl = "https://image.tmdb.org/t/p/w185/"

    companion object {
        fun newInstance(parent: ViewGroup, imageLoader: ImageLoader) =
            MovieViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.model_movie, parent, false),
                imageLoader
            )
    }

    val dataBinding = DataBindingUtil.bind<ModelMovieBinding>(view)

    fun bind(movie: Movie) {
        dataBinding!!.tvTitle.text = movie.title
        dataBinding.tvDescription.text = movie.overview
        dataBinding.tvVoteAverage.text = "⭐️ ${movie.voteAverage}"
        imageLoader.load(baseUrl + movie.posterPath, dataBinding.ivThumbnail)
    }
}
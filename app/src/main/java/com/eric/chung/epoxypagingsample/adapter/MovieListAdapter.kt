package com.eric.chung.epoxypagingsample.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.eric.chung.epoxypagingsample.data.Movie
import com.eric.chung.epoxypagingsample.util.ImageLoader
import com.eric.chung.epoxypagingsample.viewholder.MovieViewHolder

class MovieListAdapter(private val imageLoader: ImageLoader) : PagingDataAdapter<Movie, MovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.newInstance(parent, imageLoader)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = (oldItem.id == newItem.id)
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = (oldItem == newItem)
        }
    }

}
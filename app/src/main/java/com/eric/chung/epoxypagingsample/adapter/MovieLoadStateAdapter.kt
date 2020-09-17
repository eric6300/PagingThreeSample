package com.eric.chung.epoxypagingsample.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.eric.chung.epoxypagingsample.viewholder.LoadStateViewHolder

class MovieLoadStateAdapter(private val adapter: MovieListAdapter) : LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder.newInstance(parent) { adapter.retry() }
    }
}
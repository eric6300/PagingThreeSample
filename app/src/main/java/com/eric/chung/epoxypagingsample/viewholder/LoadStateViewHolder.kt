package com.eric.chung.epoxypagingsample.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.eric.chung.epoxypagingsample.R
import com.eric.chung.epoxypagingsample.databinding.HolderLoadStateBinding

class LoadStateViewHolder(view: View, private val retry: () -> Unit) : RecyclerView.ViewHolder(view) {

    companion object {
        fun newInstance(parent: ViewGroup, retry: () -> Unit) =
            LoadStateViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.holder_load_state, parent, false),
                retry
            )
    }

    val dataBinding = DataBindingUtil.bind<HolderLoadStateBinding>(view)

    fun bind(loadState: LoadState) {
        dataBinding!!.progressBar.isVisible = loadState is LoadState.Loading
        dataBinding.tvErrorMessage.isVisible = loadState is LoadState.Error
        dataBinding.btnRetry.isVisible = loadState is LoadState.Error
        dataBinding.btnRetry.setOnClickListener { retry.invoke() }

        if (loadState is LoadState.Error) {
            dataBinding.tvErrorMessage.text = loadState.error.localizedMessage
        }
    }
}
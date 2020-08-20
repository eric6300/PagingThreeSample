package com.eric.chung.epoxypagingsample.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.eric.chung.epoxypagingsample.data.NetworkState
import com.eric.chung.epoxypagingsample.data.Movie

interface DataSource {
    fun getTopRatedMovies(): LiveData<PagedList<Movie>>

    fun getNetworkState(): LiveData<NetworkState>
}
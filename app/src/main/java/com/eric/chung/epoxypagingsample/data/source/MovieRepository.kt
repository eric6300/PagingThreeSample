package com.eric.chung.epoxypagingsample.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.eric.chung.epoxypagingsample.data.NetworkState
import com.eric.chung.epoxypagingsample.data.Movie

class MovieRepository(
    private val remoteDataSource: MovieRemoteDataSource
) : Repository {

    override fun getTopRatedMovies(): LiveData<PagedList<Movie>> = remoteDataSource.getTopRatedMovies()

    override fun getNetworkState(): LiveData<NetworkState> = remoteDataSource.getNetworkState()
}
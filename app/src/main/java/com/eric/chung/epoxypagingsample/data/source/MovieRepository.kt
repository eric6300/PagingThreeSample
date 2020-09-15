package com.eric.chung.epoxypagingsample.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.eric.chung.epoxypagingsample.data.Movie
import io.reactivex.Flowable

class MovieRepository(private val remoteDataSource: MovieRemoteDataSource) : Repository {

    override fun getTopRatedMovies(): LiveData<PagingData<Movie>> = remoteDataSource.getTopRatedMovies()

}
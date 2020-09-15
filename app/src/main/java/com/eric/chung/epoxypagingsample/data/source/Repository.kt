package com.eric.chung.epoxypagingsample.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.eric.chung.epoxypagingsample.data.Movie
import io.reactivex.Flowable

interface Repository {
    fun getTopRatedMovies(): LiveData<PagingData<Movie>>
}
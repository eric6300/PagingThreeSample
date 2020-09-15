package com.eric.chung.epoxypagingsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eric.chung.epoxypagingsample.data.source.MovieRemoteDataSource
import com.eric.chung.epoxypagingsample.data.source.MovieRepository

class MainViewModel : ViewModel() {

    private val movieRepository = MovieRepository(MovieRemoteDataSource(viewModelScope))

    fun getTopRatedMovies() = movieRepository.getTopRatedMovies()

}
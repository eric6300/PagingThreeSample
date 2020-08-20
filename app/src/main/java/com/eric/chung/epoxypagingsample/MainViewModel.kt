package com.eric.chung.epoxypagingsample

import androidx.lifecycle.ViewModel
import com.eric.chung.epoxypagingsample.data.source.MovieRemoteDataSource
import com.eric.chung.epoxypagingsample.data.source.MovieRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val movieRepository = MovieRepository(MovieRemoteDataSource(compositeDisposable))

    fun getTopRatedMovies() = movieRepository.getTopRatedMovies()

    fun getNetworkState() = movieRepository.getNetworkState()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
package com.eric.chung.epoxypagingsample.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.rxjava3.disposables.CompositeDisposable

//class MovieListDataSourceFactory(compositeDisposable: CompositeDisposable) : DataSource.Factory<Int, Movie>() {
//    private val source = MovieListDataSource(compositeDisposable)
//    val sourceLiveData = MutableLiveData<MovieListDataSource>()
//
//    override fun create(): DataSource<Int, Movie> {
//        sourceLiveData.postValue(source)
//        return source
//    }
//}
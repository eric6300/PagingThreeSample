package com.eric.chung.epoxypagingsample.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.eric.chung.epoxypagingsample.network.ApiManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieListDataSource(private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, Movie>() {

    val networkState = MutableLiveData<NetworkState>()

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        networkState.postValue(NetworkState.Loading)
        compositeDisposable.add(
            ApiManager.tmdbApi.getTopRatedMovies(page = 1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        callback.onResult(it.movies ?: emptyList(), 0, it.totalResults ?: 0, null, 2)
                        networkState.postValue(NetworkState.Success)
                    },
                    onError = {
                        networkState.postValue(NetworkState.Error(it))
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        networkState.postValue(NetworkState.Loading)
        compositeDisposable.add(
            ApiManager.tmdbApi.getTopRatedMovies(params.key)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        callback.onResult(it.movies ?: emptyList(), it.page?.plus(1))
                        networkState.postValue(NetworkState.Success)
                    },
                    onError = {
                        networkState.postValue(NetworkState.Error(it))
                    }
                )
        )
    }
}
package com.eric.chung.epoxypagingsample.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.rxjava2.RxPagingSource
import com.eric.chung.epoxypagingsample.network.ApiManager
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieListDataSource : RxPagingSource<Int, Movie>() {

    private val networkState = MutableLiveData<NetworkState>()

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Movie>> {
        val currentKey = params.key ?: 1
        networkState.postValue(NetworkState.Loading)
        return ApiManager.tmdbApi.getTopRatedMovies(currentKey)
            .subscribeOn(Schedulers.io())
            .map {
                networkState.postValue(NetworkState.Success)
                val prevKey = if (currentKey == 1) null else currentKey.minus(1)
                LoadResult.Page<Int, Movie>(
                    data = it.movies ?: emptyList(),
                    prevKey = prevKey,
                    nextKey = currentKey.plus(1)
                ) as LoadResult<Int, Movie>
            }.onErrorReturn {
                networkState.postValue(NetworkState.Error(it))
                LoadResult.Error(it)
            }
    }

}
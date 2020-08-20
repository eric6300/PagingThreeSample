package com.eric.chung.epoxypagingsample.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.eric.chung.epoxypagingsample.data.NetworkState
import com.eric.chung.epoxypagingsample.data.Movie
import com.eric.chung.epoxypagingsample.data.MovieListDataSourceFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MovieRemoteDataSource(compositeDisposable: CompositeDisposable) : DataSource {

    private val sourceFactory = MovieListDataSourceFactory(compositeDisposable)
    private val config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(40)
        .setPageSize(40)
        .build()

    override fun getTopRatedMovies(): LiveData<PagedList<Movie>> {
        return LivePagedListBuilder(sourceFactory, config).build()
    }

    override fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkState }

}
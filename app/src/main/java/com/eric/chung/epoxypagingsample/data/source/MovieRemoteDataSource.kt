package com.eric.chung.epoxypagingsample.data.source

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.eric.chung.epoxypagingsample.data.Movie
import com.eric.chung.epoxypagingsample.data.MovieListSourceFactory
import kotlinx.coroutines.CoroutineScope

class MovieRemoteDataSource(private val viewModelScope: CoroutineScope) : DataSource {

    private val sourceFactory = MovieListSourceFactory()

    private val pager = Pager(
        config = PagingConfig(
            pageSize = 40,
            initialLoadSize = 40
        ),
        pagingSourceFactory = { sourceFactory }
    )

    override fun getTopRatedMovies(): LiveData<PagingData<Movie>> = pager.liveData.cachedIn(viewModelScope)

}
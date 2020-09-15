package com.eric.chung.epoxypagingsample.network

import com.eric.chung.epoxypagingsample.data.ResponseData
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// https://developers.themoviedb.org/3/getting-started/introduction
private const val API_KEY = "7ef6eb3d866f0396e5fd6941b0ecbabd"

interface TmdbApi {
    
    @GET("top_rated?api_key=$API_KEY&language=en-US")
    fun getTopRatedMovies(@Query("page") page: Int = 1): Single<ResponseData>
}
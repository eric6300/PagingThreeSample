package com.eric.chung.epoxypagingsample.network

object ApiManager {
    val tmdbApi : TmdbApi by lazy { retrofit.create(TmdbApi::class.java) }
}
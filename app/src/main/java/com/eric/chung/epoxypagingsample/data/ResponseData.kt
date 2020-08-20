package com.eric.chung.epoxypagingsample.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseData(
    @Json(name = "page") var page: Int? = null,
    @Json(name = "total_results") var totalResults: Int? = null,
    @Json(name = "total_pages") var totalPages: Int? = null,
    @Json(name = "results") var movies: List<Movie>? = null
) : Parcelable
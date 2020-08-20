package com.eric.chung.epoxypagingsample.epoxy.controller

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.eric.chung.epoxypagingsample.data.Movie
import com.eric.chung.epoxypagingsample.data.NetworkState
import com.eric.chung.epoxypagingsample.epoxy.model.LoadingModel_
import com.eric.chung.epoxypagingsample.epoxy.model.MovieModel_
import com.eric.chung.epoxypagingsample.epoxy.model.MyFavoriteModel_
import com.eric.chung.epoxypagingsample.util.ImageLoader

class MovieController(private val imageLoader: ImageLoader) : PagedListEpoxyController<Movie>() {

    private var networkState: NetworkState? = null

    private val myFavoriteMovies = arrayListOf(
        Movie(
            title = "Schindler's List",
            voteAverage = 8.6,
            posterPath = "/c8Ass7acuOe4za6DhSattE359gr.jpg"
        ),
        Movie(
            title = "Your Name.",
            voteAverage = 8.6,
            posterPath = "/q719jXXEzOoYaps6babgKnONONX.jpg"
        ),
        Movie(
            title = "Spirited Away",
            voteAverage = 8.5,
            posterPath = "/2TeJfUZMGolfDdW6DKhfIWqvq8y.jpg"
        ),
        Movie(
            title = "Howl's Moving Castle",
            voteAverage = 8.4,
            posterPath = "/iuFbU5jiNh8DAxLBGifZCvv3KmB.jpg"
        ),
        Movie(
            title = "The Dark Knight",
            voteAverage = 8.4,
            posterPath = "/qJ2tW6WMUDux911r6m7haRef0WH.jpg"
        )
    )

    override fun buildItemModel(currentPosition: Int, item: Movie?): EpoxyModel<*> {
        return if (item == null) {
            LoadingModel_()
                .id("loading_$currentPosition")
        } else {
            MovieModel_()
                .id("movie_$currentPosition")
                .imageLoader(imageLoader)
                .movie(item)
                .description("")
        }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        carousel {
            id("carousel")
            paddingDp(8)
            models(
                myFavoriteMovies.map {
                    MyFavoriteModel_()
                        .id(it.title)
                        .movie(it)
                        .imageLoader(imageLoader)
                }
            )
        }
        super.addModels(models)
        if (networkState == NetworkState.Loading) { LoadingModel_().id("loading") }
    }

    fun setNetworkState(networkState: NetworkState) {
        this.networkState = networkState
        requestModelBuild()
    }
}
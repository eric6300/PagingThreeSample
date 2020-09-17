package com.eric.chung.epoxypagingsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eric.chung.epoxypagingsample.adapter.MovieListAdapter
import com.eric.chung.epoxypagingsample.adapter.MovieLoadStateAdapter
import com.eric.chung.epoxypagingsample.util.ImageLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var imageLoader: ImageLoader
    private lateinit var movieListAdapter: MovieListAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        initViewModelAction()
    }

    private fun init() {
        imageLoader = ImageLoader(this)

        // first solution
//        movieListAdapter = MovieListAdapter(imageLoader).apply {
//            addLoadStateListener { loadState ->
//                if (loadState.refresh is LoadState.Loading){
//                    progressBar.visibility = View.VISIBLE
//                }
//                else{
//                    progressBar.visibility = View.GONE
//
//                    // getting the error
//                    val errorState = when {
//                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
//                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
//                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
//                        else -> null
//                    }
//                    errorState?.let {
//                        Toast.makeText(this@MainActivity, it.error.message, Toast.LENGTH_LONG).show()
//                    }
//                }
//            }
//        }

        // second solution
        movieListAdapter = MovieListAdapter(imageLoader)
        rvMovies.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvMovies.adapter = movieListAdapter.withLoadStateHeaderAndFooter(
            header = MovieLoadStateAdapter(movieListAdapter),
            footer = MovieLoadStateAdapter(movieListAdapter)
        )

        // wrong
//        rvMovies.adapter = movieListAdapter.apply {
//            withLoadStateHeaderAndFooter(
//                header = MovieLoadStateAdapter(movieListAdapter),
//                footer = MovieLoadStateAdapter(movieListAdapter)
//            )
//        }
    }

    private fun initViewModelAction() {
        viewModel.getTopRatedMovies().observe(this, Observer { movieListAdapter.submitData(lifecycle, it) })
    }
}
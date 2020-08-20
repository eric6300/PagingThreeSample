package com.eric.chung.epoxypagingsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eric.chung.epoxypagingsample.epoxy.controller.MovieController
import com.eric.chung.epoxypagingsample.util.ImageLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieController: MovieController
    private lateinit var imageLoader: ImageLoader
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        initViewModelAction()
    }

    private fun init() {
        imageLoader = ImageLoader(this)
        movieController = MovieController(imageLoader)
        rvMovies.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvMovies.adapter = movieController.adapter
    }

    private fun initViewModelAction() {
        viewModel.getTopRatedMovies().observe(this, Observer { movieController.submitList(it) })
        viewModel.getNetworkState().observe(this, Observer { movieController.setNetworkState(it) })
    }
}
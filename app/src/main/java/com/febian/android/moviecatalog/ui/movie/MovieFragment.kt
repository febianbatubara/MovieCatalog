package com.febian.android.moviecatalog.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.databinding.FragmentMovieBinding
import com.febian.android.moviecatalog.viewmodel.ViewModelFactory
import com.febian.android.moviecatalog.vo.Resource
import com.febian.android.moviecatalog.vo.Status

class MovieFragment : Fragment() {

    private lateinit var movieBinding: FragmentMovieBinding
    private val movieAdapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        movieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return movieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                this,
                factory
            )[MovieViewModel::class.java]

            viewModel.getMovies().observe(viewLifecycleOwner, movieObserver)
            setUpRecyclerView()
        }
    }

    private val movieObserver: Observer<Resource<List<MovieEntity>>> =
        Observer { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        movieAdapter.setMovies(movies.data)
                        movieAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(context, "Error loading data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    private fun setUpRecyclerView() {
        with(movieBinding.rvMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            movieBinding.rvMovies.visibility = View.GONE
            movieBinding.rvMoviesShimmerContainer.visibility = View.VISIBLE
        } else {
            movieBinding.rvMovies.visibility = View.VISIBLE
            movieBinding.rvMoviesShimmerContainer.visibility = View.GONE
        }
    }
}
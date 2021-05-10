package com.febian.android.moviecatalog.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.databinding.FragmentMovieBinding
import com.febian.android.moviecatalog.ui.movie.MovieAdapter
import com.febian.android.moviecatalog.viewmodel.ViewModelFactory

class FavMovieFragment : Fragment() {

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
            )[FavMovieViewModel::class.java]

            showLoading(true)
            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, movieObserver)
            setUpRecyclerView()
        }
    }

    private val movieObserver: Observer<List<MovieEntity>> =
        Observer { movies ->
            showLoading(false)
            movies.let { movieAdapter.setMovies(it) }
            showEmptyItem(movies.isNullOrEmpty())
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

    private fun showEmptyItem(state: Boolean) {
        if (state) {
            movieBinding.ivEmptyItem.visibility = View.VISIBLE
            movieBinding.tvEmptyItem.visibility = View.VISIBLE
        } else {
            movieBinding.ivEmptyItem.visibility = View.GONE
            movieBinding.tvEmptyItem.visibility = View.GONE
        }
    }
}
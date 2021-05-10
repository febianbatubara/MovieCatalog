package com.febian.android.moviecatalog.ui.favorite.movie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.databinding.FragmentMovieBinding
import com.febian.android.moviecatalog.ui.favorite.FavoriteActivity
import com.febian.android.moviecatalog.ui.movie.MovieAdapter
import com.febian.android.moviecatalog.utils.SortUtils
import com.febian.android.moviecatalog.viewmodel.ViewModelFactory

class FavMovieFragment : Fragment(), FavoriteActivity.DataSortListener {

    private lateinit var movieBinding: FragmentMovieBinding
    private val movieAdapter by lazy { MovieAdapter() }
    private lateinit var viewModel: FavMovieViewModel

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
            viewModel = ViewModelProvider(
                this,
                factory
            )[FavMovieViewModel::class.java]

            showLoading(true)
            viewModel.getFavoriteMovies(SortUtils.TITLE_ASC)
                .observe(viewLifecycleOwner, movieObserver)
            setUpRecyclerView()
        }
    }

    private val movieObserver: Observer<PagedList<MovieEntity>> =
        Observer { movies ->
            showLoading(false)
            movieAdapter.submitList(movies)
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as FavoriteActivity).registerDataSortListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as FavoriteActivity).unregisterDataSortListener(this)
    }

    override fun onDataSorted(sort: String) {
        viewModel.getFavoriteMovies(sort).observe(viewLifecycleOwner, movieObserver)
    }
}
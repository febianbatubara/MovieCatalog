package com.febian.android.moviecatalog.ui.favorite.tvshow

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
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.databinding.FragmentTvShowBinding
import com.febian.android.moviecatalog.ui.favorite.FavoriteActivity
import com.febian.android.moviecatalog.ui.tvshow.TvShowAdapter
import com.febian.android.moviecatalog.utils.SortUtils
import com.febian.android.moviecatalog.viewmodel.ViewModelFactory

class FavTvShowFragment : Fragment(), FavoriteActivity.DataSortListener {

    private lateinit var tvShowBinding: FragmentTvShowBinding
    private val tvShowAdapter by lazy { TvShowAdapter() }
    private lateinit var viewModel: FavTvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return tvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(
                this,
                factory
            )[FavTvShowViewModel::class.java]

            showLoading(true)
            viewModel.getFavoriteTvShows(SortUtils.TITLE_ASC)
                .observe(viewLifecycleOwner, tvShowsObserver)
            setUpRecyclerView()
        }
    }

    private val tvShowsObserver: Observer<PagedList<TvShowEntity>> =
        Observer { tvShows ->
            showLoading(false)
            tvShowAdapter.submitList(tvShows)
            showEmptyItem(tvShows.isNullOrEmpty())
        }

    private fun setUpRecyclerView() {
        with(tvShowBinding.rvTvShows) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            tvShowBinding.rvTvShows.visibility = View.GONE
            tvShowBinding.rvTvShowsShimmerContainer.visibility = View.VISIBLE
        } else {
            tvShowBinding.rvTvShows.visibility = View.VISIBLE
            tvShowBinding.rvTvShowsShimmerContainer.visibility = View.GONE
        }
    }

    private fun showEmptyItem(state: Boolean) {
        if (state) {
            tvShowBinding.ivEmptyItem.visibility = View.VISIBLE
            tvShowBinding.tvEmptyItem.visibility = View.VISIBLE
        } else {
            tvShowBinding.ivEmptyItem.visibility = View.GONE
            tvShowBinding.tvEmptyItem.visibility = View.GONE
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
        viewModel.getFavoriteTvShows(sort).observe(viewLifecycleOwner, tvShowsObserver)
    }
}
package com.febian.android.moviecatalog.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.febian.android.moviecatalog.R
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.databinding.FragmentTvShowBinding
import com.febian.android.moviecatalog.viewmodel.ViewModelFactory
import com.febian.android.moviecatalog.vo.Resource
import com.febian.android.moviecatalog.vo.Status

class TvShowFragment : Fragment() {

    private lateinit var tvShowBinding: FragmentTvShowBinding
    private val tvShowAdapter by lazy { TvShowAdapter() }

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
            val viewModel = ViewModelProvider(
                this,
                factory
            )[TvShowViewModel::class.java]

            viewModel.getTvShows().observe(viewLifecycleOwner, tvShowsObserver)
            setUpRecyclerView()
        }
    }

    private val tvShowsObserver: Observer<Resource<PagedList<TvShowEntity>>> =
        Observer { tvShows ->
            if (tvShows != null) {
                when (tvShows.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        tvShowAdapter.submitList(tvShows.data)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(
                            context,
                            getString(R.string.error_loading_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
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
}
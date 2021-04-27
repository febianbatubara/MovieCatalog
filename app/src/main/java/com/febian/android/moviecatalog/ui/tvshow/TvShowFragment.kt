package com.febian.android.moviecatalog.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.febian.android.moviecatalog.data.TvShowEntity
import com.febian.android.moviecatalog.databinding.FragmentTvShowBinding
import com.febian.android.moviecatalog.viewmodel.ViewModelFactory

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
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(
                this,
                factory
            )[TvShowViewModel::class.java]

            showLoading(true)
            viewModel.getTvShows().observe(viewLifecycleOwner, tvShowsObserver)
            setUpRecyclerView()
        }
    }

    private val tvShowsObserver: Observer<List<TvShowEntity>> =
        Observer { tvShows ->
            tvShows?.let { tvShowAdapter.setTvShows(it) }
            showLoading(false)
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
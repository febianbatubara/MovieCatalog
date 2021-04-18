package com.febian.android.moviecatalog.ui.tvshow

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.febian.android.moviecatalog.databinding.FragmentTvShowBinding

class TvShowFragment : Fragment() {

    private lateinit var tvShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return tvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)

        Handler(Looper.getMainLooper()).postDelayed({
            if (activity != null) {
                val viewModel = ViewModelProvider(
                    this,
                    ViewModelProvider.NewInstanceFactory()
                )[TvShowViewModel::class.java]

                val tvShows = viewModel.getTvShows()

                val tvShowAdapter = TvShowAdapter()
                tvShowAdapter.setTvShows(tvShows)

                with(tvShowBinding.rvTvShows) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = tvShowAdapter
                }
                showLoading(false)
            }
        }, 1000)
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
package com.febian.android.moviecatalog.ui.detail

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.febian.android.moviecatalog.R
import com.febian.android.moviecatalog.data.source.local.entity.TvShowEntity
import com.febian.android.moviecatalog.databinding.ActivityDetailBinding
import com.febian.android.moviecatalog.utils.Constant
import com.febian.android.moviecatalog.utils.NumberUtil
import com.febian.android.moviecatalog.viewmodel.ViewModelFactory
import com.febian.android.moviecatalog.vo.Resource
import com.febian.android.moviecatalog.vo.Status
import java.util.*

class TvShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private lateinit var tvShowDetailBinding: ActivityDetailBinding
    private lateinit var viewModel: TvShowDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvShowDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(tvShowDetailBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        )[TvShowDetailViewModel::class.java]
        showLoading(true)

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_TV_SHOW)
            viewModel.setSelectedTvShow(tvShowId)
            viewModel.tvShow.observe(this, tvShowDetailObserver)
        }

        tvShowDetailBinding.btnBack.setOnClickListener { this@TvShowDetailActivity.finish() }
    }

    private val tvShowDetailObserver: Observer<Resource<TvShowEntity>> =
        Observer { tvShowResource ->
            if (tvShowResource != null) {
                when (tvShowResource.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        tvShowResource.data?.let { tvShow ->
                            showDetail(tvShow)
                            tvShowDetailBinding.btnShare.setOnClickListener { shareData(tvShow) }
                        }
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(
                            this,
                            getString(R.string.error_loading_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }


    private fun shareData(tvShow: TvShowEntity) {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                getString(R.string.share_tv_show_message, tvShow.title, tvShow.releaseDate)
            )
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_tv_show_title)))
    }

    private fun showDetail(tvShow: TvShowEntity) {
        with(tvShowDetailBinding) {
            tvTitle.text = tvShow.title
            tvReleaseDate.text = getString(R.string.release_date, tvShow.releaseDate)
            tvTagLine.text = if (!tvShow.tagline.isNullOrBlank()) tvShow.tagline else "-"
            tvRating.text = getString(R.string.rating, tvShow.rating.toString())
            tvVoteCount.text =
                NumberUtil.formatNumber(tvShow.voteCount, this@TvShowDetailActivity)
            tvDescription.text = tvShow.description

            Glide.with(this@TvShowDetailActivity)
                .load(Constant.POSTER_PATH + tvShow.posterPath)
                .transform(RoundedCorners(16))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(ivPoster)

            Glide.with(this@TvShowDetailActivity)
                .load(Constant.POSTER_BG_PATH + tvShow.posterBgPath)
                .placeholder(ColorDrawable(Color.LTGRAY))
                .into(ivPosterBg)
        }
    }

    private fun showLoading(state: Boolean) {
        with(tvShowDetailBinding) {
            if (state) {
                ivPoster.visibility = View.GONE
                ivPosterBg.visibility = View.GONE
                tvReleaseDate.visibility = View.GONE
                tvOverviewTitle.visibility = View.GONE
                detailShimmerContainer.visibility = View.VISIBLE
            } else {
                ivPoster.visibility = View.VISIBLE
                ivPosterBg.visibility = View.VISIBLE
                tvReleaseDate.visibility = View.VISIBLE
                tvOverviewTitle.visibility = View.VISIBLE
                detailShimmerContainer.visibility = View.GONE
            }
        }
    }
}
package com.febian.android.moviecatalog.ui.detail

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.febian.android.moviecatalog.R
import com.febian.android.moviecatalog.data.TvShowEntity
import com.febian.android.moviecatalog.databinding.ActivityMovieDetailBinding

class TvShowDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private lateinit var detailBinding: ActivityMovieDetailBinding
    private lateinit var viewModel: TvShowDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[TvShowDetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_TV_SHOW)
            if (tvShowId != null) {
                viewModel.setSelectedTvShow(tvShowId)
                showDetail(viewModel.getTvShow())
            }
        }

        detailBinding.btnBack.setOnClickListener { this@TvShowDetailActivity.finish() }
        detailBinding.btnShare.setOnClickListener { shareData(viewModel.getTvShow()) }
    }

    private fun shareData(tvShow: TvShowEntity) {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                """
                    Check out this awesome tv show.
                    
                    Title: ${tvShow.title}
                    Genre: ${tvShow.genreIds}
                    Rating: "${tvShow.rating}/10"
                    Release date: ${tvShow.releaseDate}
                """.trimIndent()
            )
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share tv show information to.."))
    }

    private fun showDetail(tvShow: TvShowEntity) {
        with(detailBinding) {
            tvTitle.text = tvShow.title
            tvReleaseDate.text = getString(R.string.release_date, tvShow.releaseDate)
            tvGenre.text = tvShow.genreIds.toString()
            tvRating.text = getString(R.string.rating, tvShow.rating.toString())
            tvDescription.text = tvShow.description

            Glide.with(this@TvShowDetailActivity)
                .load(tvShow.posterPath)
                .transform(RoundedCorners(16))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(ivPoster)

            Glide.with(this@TvShowDetailActivity)
                .load(tvShow.posterBgPath)
                .placeholder(ColorDrawable(Color.LTGRAY))
                .into(ivPosterBg)
        }
    }
}
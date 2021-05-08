package com.febian.android.moviecatalog.ui.detail

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.febian.android.moviecatalog.R
import com.febian.android.moviecatalog.data.source.local.entity.MovieEntity
import com.febian.android.moviecatalog.databinding.ActivityDetailBinding
import com.febian.android.moviecatalog.utils.Constant
import com.febian.android.moviecatalog.viewmodel.ViewModelFactory

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var movieDetailBinding: ActivityDetailBinding
    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(movieDetailBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        )[MovieDetailViewModel::class.java]
        showLoading(true)

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)
            viewModel.setSelectedMovie(movieId)
            viewModel.getMovie().observe(this, movieDetailObserver)
        }

        movieDetailBinding.btnBack.setOnClickListener { this@MovieDetailActivity.finish() }
    }

    private val movieDetailObserver: Observer<MovieEntity> =
        Observer { movie ->
            showLoading(false)
            showDetail(movie)
            movieDetailBinding.btnShare.setOnClickListener { shareData(movie) }
        }

    private fun shareData(movie: MovieEntity) {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                getString(R.string.share_movie_message, movie.title, movie.releaseDate)
            )
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_movie_title)))
    }

    private fun showDetail(movie: MovieEntity) {
        with(movieDetailBinding) {
            tvTitle.text = movie.title
            tvReleaseDate.text = getString(R.string.release_date, movie.releaseDate)

            val genreList = ArrayList<String>()
            movie.genres?.forEach {
                genreList.add(it.name)
            }
            tvGenre.text = genreList.joinToString(", ")
            tvRating.text = getString(R.string.rating, movie.rating.toString())
            tvDescription.text = movie.description

            Glide.with(this@MovieDetailActivity)
                .load(Constant.POSTER_PATH + movie.posterPath)
                .transform(RoundedCorners(16))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(ivPoster)

            Glide.with(this@MovieDetailActivity)
                .load(Constant.POSTER_BG_PATH + movie.posterBgPath)
                .placeholder(ColorDrawable(Color.LTGRAY))
                .into(ivPosterBg)
        }
    }

    private fun showLoading(state: Boolean) {
        with(movieDetailBinding) {
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
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
import com.febian.android.moviecatalog.data.MovieEntity
import com.febian.android.moviecatalog.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var movieDetailBinding: ActivityMovieDetailBinding
    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieDetailBinding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(movieDetailBinding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MovieDetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                showDetail(viewModel.getMovie())
            }
        }

        movieDetailBinding.btnBack.setOnClickListener { this@MovieDetailActivity.finish() }
        movieDetailBinding.btnShare.setOnClickListener { shareData(viewModel.getMovie()) }
    }

    private fun shareData(movie: MovieEntity) {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                """
                    Check out this awesome movie.
                    
                    Title: ${movie.title}
                    Genre: ${movie.genre}
                    Rating: "${movie.rating}/10"
                    Release date: ${movie.releaseDate}
                """.trimIndent()
            )
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share movie information to.."))
    }

    private fun showDetail(movie: MovieEntity) {
        with(movieDetailBinding) {
            tvTitle.text = movie.title
            tvReleaseDate.text = getString(R.string.release_date, movie.releaseDate)
            tvGenre.text = movie.genre
            tvRating.text = getString(R.string.rating, movie.rating)
            tvDescription.text = movie.description

            Glide.with(this@MovieDetailActivity)
                .load(movie.posterPath)
                .transform(RoundedCorners(16))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(ivPoster)

            Glide.with(this@MovieDetailActivity)
                .load(movie.posterBgPath)
                .placeholder(ColorDrawable(Color.LTGRAY))
                .into(ivPosterBg)
        }
    }
}
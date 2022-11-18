package com.valijonboy.imdbsinema.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.valijonboy.imdbsinema.R
import com.valijonboy.imdbsinema.data.model.Movie
import com.valijonboy.imdbsinema.databinding.ActivityDetailsBinding
import com.valijonboy.imdbsinema.di.Injector
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class DetailsActivity : MvpAppCompatActivity(), DetailsView {

    init {
        Injector.appComponent.inject(this)
    }

    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    private val IMDB_BASE_URL = "https://www.imdb.com/title/"

    @ProvidePresenter
    fun providePresenter(): DetailsPresenter {
        val imdbID: String = intent?.extras?.getString("imdbID" ) as String
        val isFavorite: Int = intent.extras?.getInt("isFavorite") as Int
        return DetailsPresenter(imdbID, isFavorite)
    }

     lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLeaveComment.setOnClickListener{
            val comment: String = binding.etComment.text.toString()
            binding.tvComment.text = comment
            binding.etComment.editableText.clear()
            binding.etComment.isTextInputLayoutFocusedRectEnabled = false
        }

        binding.toolbar.setOnClickListener {
            finish()
        }
    }

    override fun showMovies(movie: Movie) {
        movie.poster.let {
            Glide.with(this)
                .load(it)
                .placeholder(R.drawable.ic_image)
                .into(binding.posterIv)
        }

        binding.titleMovie.text = movie.title
        binding.movieYear.text  = movie.year
        binding.typeMovie.text = movie.type
        binding.countVotes.text = movie.voted

        binding.buttonImdb.setOnClickListener{
            val url:String? = IMDB_BASE_URL + movie.imdbID
            if (!url.isNullOrEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)

            }
        }
    }

    override fun setFavoriteButton(favorite: Int) {
        binding.imageLike.setOnClickListener {
            binding.imageLike.setImageResource(
                if (favorite == 0) R.drawable.liked else R.drawable.like
            )
        }

    }

    override fun showProgress(show: Boolean) {
        binding.progressCircular.isVisible = show
    }

    override fun showToast(message: String) {

    }

    override fun showToast(message: Int) {
        TODO("Not yet implemented")
    }

    override fun showDialog(message: String?) {
        TODO("Not yet implemented")
    }
}
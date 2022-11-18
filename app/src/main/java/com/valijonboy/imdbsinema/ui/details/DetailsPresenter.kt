package com.valijonboy.imdbsinema.ui.details

import com.valijonboy.imdbsinema.app.base.BasePresentor
import com.valijonboy.imdbsinema.data.api.ApiRepository
import com.valijonboy.imdbsinema.data.api.CallbackWrapper
import com.valijonboy.imdbsinema.data.model.Movie
import com.valijonboy.imdbsinema.data.model.MovieResponse
import com.valijonboy.imdbsinema.di.Injector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class DetailsPresenter(
    private val imdbId: String,
    private val isFavorite: Int
) : BasePresentor<DetailsView>() {


    init {
        Injector.appComponent.inject(this)
    }

    @Inject
    lateinit var apiRepository: ApiRepository

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getDetailsMovie()
        checkIsFavorite()
    }

    private fun getDetailsMovie() {
        addDisposable(apiRepository.getDetailsMovieByIdImdb(imdbId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress(true) }
            .subscribeWith(object : CallbackWrapper<Movie>(viewState) {
                override fun onSuccess(t: Movie) {
                    viewState.showProgress(false)
                    viewState.showMovies(t)
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    viewState.showToast("Something went error")
                }
            })
        )
    }

   private fun checkIsFavorite() {
        viewState.setFavoriteButton(isFavorite)
    }
}
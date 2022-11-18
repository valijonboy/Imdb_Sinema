package com.valijonboy.imdbsinema.ui.main

import android.util.Log
import com.valijonboy.imdbsinema.app.base.BasePresentor
import com.valijonboy.imdbsinema.data.api.ApiRepository
import com.valijonboy.imdbsinema.data.api.CallbackWrapper
import com.valijonboy.imdbsinema.data.model.MovieResponse
import com.valijonboy.imdbsinema.di.Injector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class MainPresenter : BasePresentor<MainView>() {

    var isFavorite = false

    @Inject
    lateinit var apiRepository: ApiRepository

    init {
        Injector.appComponent.inject(this)
    }

    fun getMovies(title: String, page: Int) {
        addDisposable(apiRepository.getMoviesBySearch(title, page)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress(true) }
            .subscribeWith(object : CallbackWrapper<MovieResponse>(viewState) {
                override fun onSuccess(t: MovieResponse) {
                    viewState.showErrorText(false)
                    Log.d("presenter", "onNext: list is ${t.movieList}")
                    viewState.showProgress(false)
                    if (t.responseStatus == true) {
                        viewState.showMovies(t.movieList)
                    } else {
                        viewState.showProgress(false)
                        viewState.showErrorText(true)
                    }

                }

                override fun onError(e: Throwable) {
                    viewState.showProgress(false)
                    viewState.showErrorText(true)
                }

            })
        )
    }

}
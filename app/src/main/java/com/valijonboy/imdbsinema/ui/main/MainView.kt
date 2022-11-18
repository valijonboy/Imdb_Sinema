package com.valijonboy.imdbsinema.ui.main

import com.valijonboy.imdbsinema.app.base.BaseView
import com.valijonboy.imdbsinema.data.model.Movie
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MainView : BaseView{

//    fun showBottomSheet()
    fun showMovies(list: List<Movie>?)
    fun setFavoriteButton(favorite: Boolean)
    fun openDetailScreen()
    fun showProgress(show: Boolean)
    fun showErrorText(show: Boolean)
}
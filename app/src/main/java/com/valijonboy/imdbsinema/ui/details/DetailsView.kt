package com.valijonboy.imdbsinema.ui.details

import com.valijonboy.imdbsinema.app.base.BaseView
import com.valijonboy.imdbsinema.data.model.Movie
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface DetailsView: BaseView {

    fun showMovies(movie: Movie)
    fun setFavoriteButton(favorite: Int)
    fun showProgress(show: Boolean)
}
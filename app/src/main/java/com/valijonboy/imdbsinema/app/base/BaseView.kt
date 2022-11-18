package com.valijonboy.imdbsinema.app.base

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface BaseView: MvpView {

    fun showToast(message: String)
    fun showDialog(message: String?)
    fun showToast(message: Int)
}
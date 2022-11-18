package com.valijonboy.imdbsinema.app.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter

abstract class BasePresentor<V : BaseView> : MvpPresenter<V>() {

    var mCompositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable): Disposable {
        mCompositeDisposable.add(disposable)
        return disposable
    }

    override fun onDestroy() {
        disposeAll()
    }

    private fun disposeAll() {
        mCompositeDisposable.dispose()
    }

    open fun onRefresh(){
//        mCompositeDisposable.dispose()
        onFirstViewAttach()
    }
}
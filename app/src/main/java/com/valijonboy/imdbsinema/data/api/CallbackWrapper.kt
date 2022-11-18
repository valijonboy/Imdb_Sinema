package com.valijonboy.imdbsinema.data.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.valijonboy.imdbsinema.app.base.BaseResponse
import com.valijonboy.imdbsinema.app.base.BaseView
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import java.lang.ref.WeakReference
import java.net.UnknownHostException

abstract class CallbackWrapper<T : BaseResponse>(val view: BaseView) : DisposableObserver<T>() {

    val gson = Gson()

    //BaseView is just a reference of a View in MVP
    var weakReference: WeakReference<BaseView> = WeakReference(view)

    protected abstract fun onSuccess(t: T)

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        val view = weakReference.get()

        e.printStackTrace()
    }

    override fun onComplete() {
    }


}
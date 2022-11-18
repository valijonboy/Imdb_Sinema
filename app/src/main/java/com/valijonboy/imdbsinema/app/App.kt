package com.valijonboy.imdbsinema.app

import android.app.Application
import com.valijonboy.imdbsinema.di.Injector

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initInjector()
    }

    private fun initInjector() {
        Injector.initAppComponent(this)
    }
}
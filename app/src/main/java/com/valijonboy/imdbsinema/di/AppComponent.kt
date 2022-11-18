package com.valijonboy.imdbsinema.di

import com.valijonboy.imdbsinema.di.module.AppModule
import com.valijonboy.imdbsinema.di.module.DataModule
import com.valijonboy.imdbsinema.ui.details.DetailsActivity
import com.valijonboy.imdbsinema.ui.details.DetailsPresenter
import com.valijonboy.imdbsinema.ui.main.MainActivity
import com.valijonboy.imdbsinema.ui.main.MainAdapter
import com.valijonboy.imdbsinema.ui.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainAdapter: MainAdapter)
    fun inject(detailsActivity: DetailsActivity)
    fun inject(detailsPresenter: DetailsPresenter)
}
package com.valijonboy.imdbsinema.data.api

import com.valijonboy.imdbsinema.BuildConfig.API_KEY
import com.valijonboy.imdbsinema.data.model.Movie
import com.valijonboy.imdbsinema.data.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/?apikey=$API_KEY")
    fun getMoviesBySearch(@Query("s") title: String, @Query("page") page: Int) :
            Observable<MovieResponse>

    @GET("/?apikey=$API_KEY")
    fun getDetailsMovieByIdImdb(@Query("i") imdbId: String) : Observable<Movie>
}
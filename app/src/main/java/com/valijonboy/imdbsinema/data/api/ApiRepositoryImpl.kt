package com.valijonboy.imdbsinema.data.api

import com.valijonboy.imdbsinema.data.model.Movie
import com.valijonboy.imdbsinema.data.model.MovieResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): ApiRepository{

    override fun getMoviesBySearch(title: String, page: Int): Observable<MovieResponse> =
        apiService.getMoviesBySearch(title, page).subscribeOn(Schedulers.io())

    override fun getDetailsMovieByIdImdb(imdbId: String): Observable<Movie> =
        apiService.getDetailsMovieByIdImdb(imdbId).subscribeOn(Schedulers.io())
}
package com.valijonboy.imdbsinema.data.api

import com.valijonboy.imdbsinema.data.model.Movie
import com.valijonboy.imdbsinema.data.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.Path

interface ApiRepository {
   fun getMoviesBySearch(title: String, page: Int) : Observable<MovieResponse>

   fun getDetailsMovieByIdImdb(imdbId: String): Observable<Movie>
}
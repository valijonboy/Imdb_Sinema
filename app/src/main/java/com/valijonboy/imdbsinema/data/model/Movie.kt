package com.valijonboy.imdbsinema.data.model

import com.google.gson.annotations.SerializedName
import com.valijonboy.imdbsinema.app.base.BaseResponse
import java.io.Serializable

data class MovieResponse(

    @SerializedName("Search")
    val movieList: List<Movie>? = null,

    @SerializedName("Response")
    val responseStatus: Boolean? = false
): BaseResponse()

data class Movie(

    @SerializedName("Title")
    var title: String? = null,

    @SerializedName("Year")
    var year: String? = null,

    @SerializedName("imdbID")
    var imdbID: String? = null,

    @SerializedName("Type")
    var type: String? = null,

    @SerializedName("Poster")
    val poster: String? = null,

    @SerializedName("imdbVotes")
    var voted: String? = null
): BaseResponse(), Serializable

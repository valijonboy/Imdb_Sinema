package com.valijonboy.imdbsinema.utils

import android.widget.ImageView

interface ImageUtil {
    fun loadImage(imageView: ImageView, url: String?, placeholder: Int? = null, blur: Int? = null, error: Int? = null)
}
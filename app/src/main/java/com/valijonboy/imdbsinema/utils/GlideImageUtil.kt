package com.valijonboy.imdbsinema.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageUtil(val context: Context): ImageUtil {

    override fun loadImage(
        imageView: ImageView,
        url: String?,
        placeholder: Int?,
        blur: Int?,
        error: Int?
    ) {
        if (placeholder != null) Glide.with(context).load(url).placeholder(placeholder)
            .into(imageView)
        else if (error != null) Glide.with(context).load(url).error(error).into(imageView)
        else Glide.with(context).load(url).into(imageView)
    }
}
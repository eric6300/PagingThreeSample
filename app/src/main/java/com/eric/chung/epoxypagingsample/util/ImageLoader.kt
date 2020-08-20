package com.eric.chung.epoxypagingsample.util

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImageLoader(
    private var requestManager: RequestManager,
    private var context: Context
) {
    constructor(activity: Activity) : this(Glide.with(activity), activity)

    fun load(url: String?, iv: ImageView) {
        if (isDestroy(context)) return
        Glide.with(context).load(url).transition(DrawableTransitionOptions.withCrossFade()).into(iv)
    }

    private fun isDestroy(context: Context?): Boolean {
        if (context == null) return true
        if (context is Activity && context.isFinishing) return true
        return false
    }
}
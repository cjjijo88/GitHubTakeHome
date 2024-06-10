package com.assignment.githubtakehome.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * @Author: Jijo
 * @Date: 10-06-2024
 */
object ImageBindingAdapter {

    /**
     * This Binding adapter function to load an image from a URL into an ImageView using Glide.
     *
     * @param view The ImageView to load the image into.
     * @param url The URL of the image to load.
     */
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?) {
        url?.let {
            Glide.with(view.context)
                .load(it)
                .into(view)
        }
    }
}
package com.mudassirkhan.wheeloffate.util

import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.ajalt.timberkt.v


@BindingAdapter("android:visibility")
    fun setVisibility(view: View, value: Boolean?) {
        view.visibility = if (value!!) View.VISIBLE else View.GONE
    }


    @BindingAdapter("bind:imageBitmap")
    fun setBitmap(view: ImageView, bitmap: Bitmap?) {
        view.setImageBitmap(bitmap)
    }

    @BindingAdapter("android:src")
    fun setImageUrl(imageView: ImageView, resource: Int) {
        if (resource != 0) {
            imageView.setImageResource(resource)
        }
    }

    @BindingAdapter("loadUrl")
    fun loadUrl(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView.context)
                .load(it)
                .apply(RequestOptions.noTransformation())
                .into(imageView)
        }
    }

  @BindingAdapter("android:text")
  fun addValue(view: TextView,value:Int){
      view.setText(Integer.toString(value))
//
  }

package com.yxnsx.sopt.week02

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bindDrawable")
fun bindDrawable(view: ImageView, image: String) {
    Glide.with(view)
        .load(image)
        .circleCrop()
        .into(view)
}

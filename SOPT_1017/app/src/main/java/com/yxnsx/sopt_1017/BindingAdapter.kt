package com.yxnsx.sopt_1017

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

@BindingAdapter("bindDrawable")
fun bindImageFromRes(view: ImageView, @DrawableRes image: Int) {
    view.setImageResource(image)
}

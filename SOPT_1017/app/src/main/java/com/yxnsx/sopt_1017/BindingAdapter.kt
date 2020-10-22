package com.yxnsx.sopt_1017

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("bindDrawable")
fun bindImageFromRes(view: ImageView, image: Int) {
    view.setBackgroundResource(image)
}

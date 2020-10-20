package com.yxnsx.sopt_1017

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.item_title)
    private val subTitle: TextView = itemView.findViewById(R.id.item_subTitle)

    fun onBind(data: ProfileData) {
        title.text = data.title
        subTitle.text = data.subTitle
    }
}
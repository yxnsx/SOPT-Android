package com.yxnsx.sopt_1017

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter(private val context: Context) : RecyclerView.Adapter<ProfileViewHolder>() {

    var dataList = mutableListOf<ProfileData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holderViewHolder: ProfileViewHolder, position: Int) {
        holderViewHolder.onBind(dataList[position])
    }
}
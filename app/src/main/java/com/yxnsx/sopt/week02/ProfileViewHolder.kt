package com.yxnsx.sopt.week02

import androidx.recyclerview.widget.RecyclerView
import com.yxnsx.sopt.databinding.ItemRecyclerViewBinding

class ProfileViewHolder(val dataBinding: ItemRecyclerViewBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {

    fun onBind(profileData: ProfileData) {
        dataBinding.profile = profileData
    }
}
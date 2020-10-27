package com.yxnsx.sopt_1017.week_02

import androidx.recyclerview.widget.RecyclerView
import com.yxnsx.sopt_1017.databinding.ItemRecyclerViewBinding
import com.yxnsx.sopt_1017.week_02.ProfileData

class ProfileViewHolder(val dataBinding: ItemRecyclerViewBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {

    fun onBind(profileData: ProfileData) {
        dataBinding.profile = profileData
    }
}
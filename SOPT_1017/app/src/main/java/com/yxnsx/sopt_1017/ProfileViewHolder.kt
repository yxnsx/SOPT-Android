package com.yxnsx.sopt_1017

import androidx.recyclerview.widget.RecyclerView
import com.yxnsx.sopt_1017.databinding.ItemRecyclerViewBinding

class ProfileViewHolder(val itemBinding: ItemRecyclerViewBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun onBind(profileData: ProfileData) {
        itemBinding.profile = profileData
    }
}
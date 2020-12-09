package com.yxnsx.sopt.week02

import androidx.recyclerview.widget.RecyclerView
import com.yxnsx.sopt.databinding.ItemRecyclerViewBinding

class UserViewHolder(val dataBinding: ItemRecyclerViewBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {

    fun onBind(userData: UserData) {
        dataBinding.profile = userData
    }
}
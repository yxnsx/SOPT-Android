package com.yxnsx.sopt_1017

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView


class ProfileAdapter(
    private var dataList: List<ProfileData>,
    private val profileItemListener: ProfileItemListener
) : RecyclerView.Adapter<ProfileViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recycler_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(profileViewHolder: ProfileViewHolder, position: Int) {

        profileViewHolder.apply {
            onBind(dataList[position])
            dataBinding.root.setOnClickListener {
                profileItemListener.onClickProfileItem(it, dataList[position])
            }
        }
    }


    fun setLiveData(newData: List<ProfileData>) {
        dataList = newData
        notifyDataSetChanged()
    }
}
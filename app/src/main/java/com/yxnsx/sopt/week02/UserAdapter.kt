package com.yxnsx.sopt.week02

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yxnsx.sopt.*
import com.yxnsx.sopt.databinding.ItemRecyclerViewBinding
import com.yxnsx.sopt.util.ProfileItemClickListener


class UserAdapter(
    private var dataList: MutableList<UserData>,
    private val profileItemClickListener: ProfileItemClickListener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(userViewHolder: UserViewHolder, position: Int) {
        userViewHolder.dataBinding.apply {

            profile = dataList[position]
            root.setOnClickListener {
                profileItemClickListener.onClickProfileItem(it, dataList[position])
            }
        }
    }

    fun setLiveData(newData: MutableList<UserData>) {
        dataList = newData
        notifyDataSetChanged()
    }

    inner class UserViewHolder(
        val dataBinding: ItemRecyclerViewBinding
    ) : RecyclerView.ViewHolder(dataBinding.root)
}
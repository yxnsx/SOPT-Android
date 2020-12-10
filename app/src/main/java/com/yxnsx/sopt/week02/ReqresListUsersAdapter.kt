package com.yxnsx.sopt.week02

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yxnsx.sopt.R
import com.yxnsx.sopt.databinding.ItemReqresListUserBinding
import com.yxnsx.sopt.util.ItemListeners
import com.yxnsx.sopt.week02.reqres_api.ReqresListUsersModel


class ReqresListUsersAdapter(
    private val reqresListUsers: List<ReqresListUsersModel.Data>,
    private val clickListener: ItemListeners
) : RecyclerView.Adapter<ReqresListUsersAdapter.ReqresListUsersViewHolder>() {


    override fun getItemCount() = reqresListUsers.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReqresListUsersViewHolder {
        return ReqresListUsersViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_reqres_list_user,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReqresListUsersViewHolder, position: Int) {
        holder.dataBinding.listUsers = reqresListUsers[position]
        holder.dataBinding.root.setOnClickListener {
            clickListener.onClickReqresListUsersItem(it, reqresListUsers[position])
        }
    }

    inner class ReqresListUsersViewHolder(
        val dataBinding: ItemReqresListUserBinding
    ) : RecyclerView.ViewHolder(dataBinding.root)
}
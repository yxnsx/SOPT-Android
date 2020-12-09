package com.yxnsx.sopt.week06.kakao_search_api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yxnsx.sopt.R
import com.yxnsx.sopt.databinding.ItemKakaoSearchBinding
import com.yxnsx.sopt.util.KakaoSearchItemClickListener

class KakaoSearchAdapter(
    private val searchResultList: List<KakaoSearchModel.Document>,
    private val clickListener: KakaoSearchItemClickListener
) : RecyclerView.Adapter<KakaoSearchAdapter.KakaoSearchViewHolder>() {

    override fun getItemCount() = searchResultList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        KakaoSearchViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_kakao_search,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: KakaoSearchViewHolder, position: Int) {
        holder.dataBinding.searchResult = searchResultList[position]
        holder.dataBinding.constraintLayoutItemContainer.setOnClickListener {

            clickListener.onClickKakaoSearchItem(
                holder.dataBinding.constraintLayoutItemContainer,
                searchResultList[position]
            )
        }
    }

    inner class KakaoSearchViewHolder(
        val dataBinding: ItemKakaoSearchBinding
    ) : RecyclerView.ViewHolder(dataBinding.root)
}
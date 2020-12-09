package com.yxnsx.sopt.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yxnsx.sopt.week02.UserData
import com.yxnsx.sopt.week06.kakao_search_api.KakaoSearchModel

interface ProfileItemClickListener {
    fun onClickProfileItem(view: View, userData: UserData)
}

interface ProfileItemDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}

interface ProfileItemActionListener {
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwiped(position: Int)
}

interface KakaoSearchItemClickListener {
    fun onClickKakaoSearchItem(view: View, searchResult: KakaoSearchModel.Document)
}
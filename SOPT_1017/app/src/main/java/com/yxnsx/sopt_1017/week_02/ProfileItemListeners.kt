package com.yxnsx.sopt_1017.week_02

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yxnsx.sopt_1017.week_02.ProfileData

interface ProfileItemClickListener {
    fun onClickProfileItem(view: View, profileData: ProfileData)
}

interface ProfileItemDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}

interface ProfileItemActionListener {
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwiped(position: Int)
}
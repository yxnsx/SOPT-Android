package com.yxnsx.sopt.week02

import android.view.View
import androidx.recyclerview.widget.RecyclerView

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
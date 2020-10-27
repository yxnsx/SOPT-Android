package com.yxnsx.sopt_1017.week_02

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yxnsx.sopt_1017.*


class ProfileAdapter(
    private var dataList: MutableList<ProfileData>,
    private val profileItemClickListener: ProfileItemClickListener,
    private val profileItemDragListener: ProfileItemDragListener
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(profileViewHolder: ProfileViewHolder, position: Int) {

        profileViewHolder.apply {
            onBind(dataList[position])
            dataBinding.root.setOnClickListener {
                profileItemClickListener.onClickProfileItem(it, dataList[position])
            }
            dataBinding.root.setOnLongClickListener {
                it.setOnTouchListener { v, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        profileItemDragListener.onStartDrag(this)
                    }
                    true // 해당 메서드에 정의한 내용만을 실행
                }
                true // 해당 메서드에 정의한 내용만을 실행
            }
        }
    }

    fun setLiveData(newData: MutableList<ProfileData>) {
        dataList = newData
        notifyDataSetChanged()
    }
}
package com.yxnsx.sopt_1017

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yxnsx.sopt_1017.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity(), ProfileItemClickListener, ProfileItemDragListener, ProfileItemActionListener {

    private val dataList = mutableListOf<ProfileData>()
    private lateinit var viewBinding: ActivityHomeBinding
    private val profileListViewModel: ProfileListViewModel by viewModels()

    private lateinit var itemTouchHelper: ItemTouchHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // viewBinding 설정
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // clickListener 설정
        viewBinding.FABGridLayout.setOnClickListener(onClickListener)

        // recyclerView 설정
        viewBinding.recyclerView.apply {
            adapter = ProfileAdapter(dataList, this@HomeActivity, this@HomeActivity)
            layoutManager = LinearLayoutManager(context)
        }

        itemTouchHelper = ItemTouchHelper(ProfileItemTouchHelperCallback(this))
        itemTouchHelper.attachToRecyclerView(viewBinding.recyclerView)

        // 뷰모델의 Observer를 통해 리사이클러뷰의 ProfileAdapter에 변경값 갱신
        profileListViewModel.profileLiveData.observe(this, Observer {
            (viewBinding.recyclerView.adapter as ProfileAdapter).setLiveData(it)
        })
    }

    private val onClickListener = View.OnClickListener {
        when (it.id) {
            viewBinding.FABGridLayout.id ->
                changeLayoutManager()
        }
    }

    private fun changeLayoutManager() {
        viewBinding.apply {
            if (recyclerView.layoutManager is GridLayoutManager) {
                recyclerView.layoutManager = LinearLayoutManager(this@HomeActivity)
                FABGridLayout.setImageResource(R.drawable.icon_grid)
            } else {
                recyclerView.layoutManager = GridLayoutManager(this@HomeActivity, 2)
                FABGridLayout.setImageResource(R.drawable.icon_list)
            }
        }
    }

    override fun onClickProfileItem(view: View, profileData: ProfileData) {

        val intent = Intent(this, ProfileDetailActivity::class.java)
        intent.putExtra("profileData", profileData)
        startActivity(intent)
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    override fun onItemMoved(from: Int, to: Int) {
        profileListViewModel.moveProfileItem(from, to)
        viewBinding.recyclerView.adapter?.notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        profileListViewModel.swipeProfileItem(position)
        viewBinding.recyclerView.adapter?.notifyItemRemoved(position)
    }
}
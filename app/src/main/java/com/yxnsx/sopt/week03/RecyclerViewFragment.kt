package com.yxnsx.sopt.week03

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yxnsx.sopt.R
import com.yxnsx.sopt.databinding.ActivityHomeBinding
import com.yxnsx.sopt.databinding.FragmentRecyclerViewBinding
import com.yxnsx.sopt.week02.*


class RecyclerViewFragment : Fragment(),
    ProfileItemClickListener,
    ProfileItemDragListener,
    ProfileItemActionListener {

    private val dataList = mutableListOf<ProfileData>()
    private lateinit var viewBinding: FragmentRecyclerViewBinding
    private val profileListViewModel: ProfileListViewModel by viewModels()

    private lateinit var itemTouchHelper: ItemTouchHelper


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentRecyclerViewBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    // ui 작업 수행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // clickListener 설정
        viewBinding.FABGridLayout.setOnClickListener(onClickListener)

        // recyclerView 설정
        viewBinding.recyclerView.apply {
            adapter = ProfileAdapter(
                dataList,
                this@RecyclerViewFragment,
                this@RecyclerViewFragment
            )
            layoutManager = LinearLayoutManager(context)
        }

        itemTouchHelper = ItemTouchHelper(
            ProfileItemTouchHelperCallback(
                this
            )
        )
        itemTouchHelper.attachToRecyclerView(viewBinding.recyclerView)

        // 뷰모델의 Observer를 통해 리사이클러뷰의 ProfileAdapter에 변경값 갱신
        profileListViewModel.profileLiveData.observe(viewLifecycleOwner, Observer {
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
                recyclerView.layoutManager = LinearLayoutManager(context)
                FABGridLayout.setImageResource(R.drawable.icon_grid)
            } else {
                recyclerView.layoutManager = GridLayoutManager(context, 2)
                FABGridLayout.setImageResource(R.drawable.icon_list)
            }
        }
    }

    override fun onClickProfileItem(view: View, profileData: ProfileData) {

        val intent = Intent(context, ProfileDetailActivity::class.java)
        intent.putExtra("profileData", profileData)
        startActivity(intent)
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    override fun onItemMoved(from: Int, to: Int) {
        profileListViewModel.moveProfileItem(from, to)
        //viewBinding.recyclerView.adapter?.notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        profileListViewModel.swipeProfileItem(position)
        //viewBinding.recyclerView.adapter?.notifyItemRemoved(position)
    }
}
package com.yxnsx.sopt.week03

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yxnsx.sopt.R
import com.yxnsx.sopt.databinding.FragmentBottomNavRecyclerViewBinding
import com.yxnsx.sopt.util.ProfileItemClickListener
import com.yxnsx.sopt.util.USER_DATA
import com.yxnsx.sopt.week02.*


class BottomNavRecyclerViewFragment : Fragment(), ProfileItemClickListener {

    private val dataList = mutableListOf<UserData>()
    private lateinit var viewBinding: FragmentBottomNavRecyclerViewBinding
    private val userListViewModel: UserListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentBottomNavRecyclerViewBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    // ui 작업 수행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        setClickListener()
        setLiveDataObserver()
    }


    private fun setClickListener() {
        viewBinding.FABGridLayout.setOnClickListener(onClickListener)
    }

    private fun setRecyclerView() {
        viewBinding.recyclerView.apply {
            adapter = UserAdapter(
                dataList,
                this@BottomNavRecyclerViewFragment
            )
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setLiveDataObserver() {
        userListViewModel.profileLiveData.observe(viewLifecycleOwner, Observer {
            (viewBinding.recyclerView.adapter as UserAdapter).setLiveData(it)
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

    override fun onClickProfileItem(view: View, userData: UserData) {
        val intent = Intent(context, UserDetailActivity::class.java)
        intent.putExtra(USER_DATA, userData)
        startActivity(intent)
    }
}
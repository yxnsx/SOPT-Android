package com.yxnsx.sopt.week03

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yxnsx.sopt.R
import com.yxnsx.sopt.databinding.FragmentBottomNavReqresListUsersBinding
import com.yxnsx.sopt.util.ItemListeners
import com.yxnsx.sopt.util.USER_DATA
import com.yxnsx.sopt.week02.*
import com.yxnsx.sopt.week06.reqres_api.*


class BottomNavReqresListUsersFragment : Fragment(), ItemListeners {

    private var layoutStatus = "list"
    private lateinit var viewBinding: FragmentBottomNavReqresListUsersBinding
    private lateinit var reqresListUsersViewModel: ReqresListUsersViewModel
    private lateinit var reqresListUsersViewModelFactory: ReqresListUsersViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentBottomNavReqresListUsersBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    // ui 작업 수행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        setLayoutManager()
        setListeners()
        reqresListUsersViewModel.getReqresUsers()
    }

    private fun setViewModel() {
        val api = ReqresListUsersApi()
        val repository = ReqresListUsersRepository(api)
        reqresListUsersViewModelFactory = ReqresListUsersViewModelFactory(repository)
        reqresListUsersViewModel = ViewModelProvider(this, reqresListUsersViewModelFactory)
            .get(ReqresListUsersViewModel::class.java)
    }

    private fun setListeners() {
        viewBinding.FABGridLayout.setOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener {
        when (it.id) {
            viewBinding.FABGridLayout.id -> {
                setLayoutManager()
            }
        }
    }

    private fun setLayoutManager() {
        removeObserverFromViewModel()
        viewBinding.apply {
            if (layoutStatus == "list") {
                FABGridLayout.setImageResource(R.drawable.icon_grid)
                setObserverToListViewModel()
                layoutStatus = "grid"

            } else {
                FABGridLayout.setImageResource(R.drawable.icon_list)
                setObserverToGridViewModel()
                layoutStatus = "list"
            }
        }
    }

    private fun setObserverToListViewModel() {
        reqresListUsersViewModel.reqresListUsersLiveData.observe(
            viewLifecycleOwner,
            Observer { reqresListUsers ->
                viewBinding.recyclerViewReqresListUsers.also {
                    it.layoutManager = LinearLayoutManager(context)
                    it.setHasFixedSize(true)
                    it.adapter = ReqresListUsersAdapter(reqresListUsers, this)
                }
            })
    }

    private fun setObserverToGridViewModel() {
        reqresListUsersViewModel.reqresListUsersLiveData.observe(
            viewLifecycleOwner,
            Observer { reqresListUsers ->
                viewBinding.recyclerViewReqresListUsers.also {
                    it.layoutManager = GridLayoutManager(context, 2)
                    it.setHasFixedSize(true)
                    it.adapter = ReqresGridUsersAdapter(reqresListUsers, this)
                }
            })
    }

    private fun removeObserverFromViewModel() {
        reqresListUsersViewModel.reqresListUsersLiveData.removeObservers(viewLifecycleOwner)
    }

    override fun onClickReqresListUsersItem(view: View, userData: ReqresListUsersModel.Data) {
        val intent = Intent(context, ReqresListUsersDetailActivity::class.java)
        intent.putExtra(USER_DATA, userData)
        startActivity(intent)
    }
}

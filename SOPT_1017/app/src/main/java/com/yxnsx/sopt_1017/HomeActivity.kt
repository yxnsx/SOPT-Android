package com.yxnsx.sopt_1017

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yxnsx.sopt_1017.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity(), ProfileItemListener {

    private lateinit var viewBinding: ActivityHomeBinding
    private val profileListViewModel: ProfileListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // viewBinding 설정
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // recyclerView 설정
        viewBinding.recyclerView.apply {
            adapter = ProfileAdapter(emptyList(), this@HomeActivity)
            layoutManager = LinearLayoutManager(context)
        }

        // 뷰모델의 Observer를 통해 리사이클러뷰의 ProfileAdapter에 변경값 갱신
        profileListViewModel.profileLiveData.observe(this, Observer {
            (viewBinding.recyclerView.adapter as ProfileAdapter).setLiveData(it)
        })
    }

    override fun onClickProfileItem(view: View, profileData: ProfileData) {

        val intent = Intent(this, ProfileDetailActivity::class.java)
        intent.putExtra("profileData", profileData)
        startActivity(intent)

        Toast.makeText(this, "click item", Toast.LENGTH_SHORT).show()
    }
}
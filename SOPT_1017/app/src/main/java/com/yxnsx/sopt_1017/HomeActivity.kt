package com.yxnsx.sopt_1017

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        profileAdapter = ProfileAdapter(this)

        recyclerView.apply {
            adapter = profileAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }

        profileAdapter.dataList = mutableListOf(
            ProfileData("이름", "최윤소"),
            ProfileData("나이", "25"),
            ProfileData("파트", "안드로이드"),
            ProfileData("github", "https://github.com/yxnsx"),
            ProfileData("SOPT", "www.sopt.org"),
            ProfileData("이름", "최윤소"),
            ProfileData("나이", "25"),
            ProfileData("파트", "안드로이드"),
            ProfileData("github", "https://github.com/yxnsx"),
            ProfileData("SOPT", "www.sopt.org")
        )

        profileAdapter.notifyDataSetChanged()
    }
}
package com.yxnsx.sopt.week02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yxnsx.sopt.databinding.ActivityProfileDetailBinding

class ProfileDetailActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityProfileDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = ActivityProfileDetailBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)

        if (intent.hasExtra("profileData")) {
            dataBinding.profile = intent.getParcelableExtra("profileData")
        }
    }
}
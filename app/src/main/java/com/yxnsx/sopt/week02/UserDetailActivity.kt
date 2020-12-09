package com.yxnsx.sopt.week02

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yxnsx.sopt.databinding.ActivityProfileDetailBinding
import com.yxnsx.sopt.util.USER_DATA
import kotlinx.android.synthetic.main.activity_profile_detail.*

class UserDetailActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityProfileDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDataBinding()
        setUserData()
        setClickListener()
    }

    private fun setDataBinding() {
        dataBinding = ActivityProfileDetailBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)
    }

    private fun setUserData() {
        if (intent.hasExtra(USER_DATA)) {
            dataBinding.profile = intent.getParcelableExtra(USER_DATA)
        }
    }

    private fun setClickListener() {
        dataBinding.imageButtonBackArrow.setOnClickListener(buttonClickListener)
    }

    private val buttonClickListener = View.OnClickListener {
        when (it) {
            imageButton_backArrow -> {
                onBackPressed()
            }
        }
    }
}
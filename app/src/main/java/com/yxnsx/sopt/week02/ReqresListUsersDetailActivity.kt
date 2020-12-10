package com.yxnsx.sopt.week02

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yxnsx.sopt.databinding.ActivityReqresListUsersDetailBinding
import com.yxnsx.sopt.util.USER_DATA


class ReqresListUsersDetailActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityReqresListUsersDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDataBinding()
        setUserData()
        setClickListener()
    }

    private fun setDataBinding() {
        dataBinding = ActivityReqresListUsersDetailBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)
    }

    private fun setUserData() {
        if (intent.hasExtra(USER_DATA)) {
            dataBinding.listUsers = intent.getParcelableExtra(USER_DATA)
        }
    }

    private fun setClickListener() {
        dataBinding.imageButtonBackArrow.setOnClickListener(buttonClickListener)
    }

    private val buttonClickListener = View.OnClickListener {
        when (it.id) {
            dataBinding.imageButtonBackArrow.id -> {
                onBackPressed()
            }
        }
    }
}
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

        dataBinding = ActivityProfileDetailBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)

        if (intent.hasExtra(USER_DATA)) {
            dataBinding.profile = intent.getParcelableExtra(USER_DATA)
        }

        dataBinding.imageButtonBackArrow.setOnClickListener(buttonClickListener)
    }

    private val buttonClickListener = View.OnClickListener {
        when(it) {
            imageButton_backArrow -> {
                onBackPressed()
            }
        }
    }
}
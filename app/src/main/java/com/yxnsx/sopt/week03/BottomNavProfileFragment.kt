package com.yxnsx.sopt.week03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yxnsx.sopt.databinding.FragmentBottomNavProfileBinding
import com.yxnsx.sopt.util.MyApplication
import com.yxnsx.sopt.util.USER_EMAIL
import com.yxnsx.sopt.util.USER_NAME


class BottomNavProfileFragment : Fragment() {

    private lateinit var viewBinding: FragmentBottomNavProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentBottomNavProfileBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    // ui 작업 수행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserInfo()
        setTabLayout()
    }

    private fun setUserInfo() {
        val userName = MyApplication.mySharedPreferences.getString(USER_NAME, "")
        val userEmail = MyApplication.mySharedPreferences.getString(USER_EMAIL, "")
        viewBinding.textViewName.text = userName
        viewBinding.textViewEmail.text = userEmail
    }

    private fun setTabLayout() {
        viewBinding.apply {
            viewPager.adapter = ProfileViewPagerAdapter(childFragmentManager)
            tabLayoutProfile.setupWithViewPager(viewBinding.viewPager)
            tabLayoutProfile.apply {
                getTabAt(0)?.text = "INFO"
                getTabAt(1)?.text = "OTHERS"
            }
        }
    }
}
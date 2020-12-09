package com.yxnsx.sopt.week03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yxnsx.sopt.databinding.FragmentBottomNavKakaoSearchBinding


class BottomNavkakaoSearchFragment : Fragment() {

    lateinit var viewBinding: FragmentBottomNavKakaoSearchBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentBottomNavKakaoSearchBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}
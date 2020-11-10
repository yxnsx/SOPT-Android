package com.yxnsx.sopt.week03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yxnsx.sopt.databinding.FragmentProfileOthersBinding


class ProfileOthersFragment : Fragment() {

    lateinit var viewBinding: FragmentProfileOthersBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentProfileOthersBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}
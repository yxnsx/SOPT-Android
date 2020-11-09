package com.yxnsx.sopt.week03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yxnsx.sopt.R
import com.yxnsx.sopt.databinding.FragmentInfoBinding
import com.yxnsx.sopt.databinding.FragmentOthersBinding


class OthersFragment : Fragment() {

    lateinit var viewBinding: FragmentOthersBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentOthersBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}
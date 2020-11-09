package com.yxnsx.sopt.week03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yxnsx.sopt.R
import com.yxnsx.sopt.databinding.FragmentEmptyBinding
import com.yxnsx.sopt.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {

    lateinit var viewBinding: FragmentInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentInfoBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}
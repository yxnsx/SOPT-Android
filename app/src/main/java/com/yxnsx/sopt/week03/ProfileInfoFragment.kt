package com.yxnsx.sopt.week03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yxnsx.sopt.databinding.FragmentProfileInfoBinding


class ProfileInfoFragment : Fragment() {

    lateinit var viewBinding: FragmentProfileInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentProfileInfoBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
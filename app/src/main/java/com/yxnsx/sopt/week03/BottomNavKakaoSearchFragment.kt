package com.yxnsx.sopt.week03

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yxnsx.sopt.R
import com.yxnsx.sopt.databinding.FragmentBottomNavKakaoSearchBinding
import com.yxnsx.sopt.util.KakaoSearchItemClickListener
import com.yxnsx.sopt.week02.UserListViewModel
import com.yxnsx.sopt.week06.kakao_search_api.*
import kotlinx.android.synthetic.main.fragment_bottom_nav_kakao_search.*
import kotlinx.android.synthetic.main.item_kakao_search.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class BottomNavKakaoSearchFragment : Fragment(), KakaoSearchItemClickListener {

    lateinit var viewBinding: FragmentBottomNavKakaoSearchBinding
    private lateinit var kakaoSearchViewModel: KakaoSearchViewModel
    private lateinit var kakaoSearchViewModelFactory: KakaoSearchViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentBottomNavKakaoSearchBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        setObserverToViewModel()
        setButtonClickListener()
    }

    private fun setViewModel() {
        val api = KakaoSearchApi()
        val repository = KakaoSearchRepository(api)
        kakaoSearchViewModelFactory = KakaoSearchViewModelFactory(repository)
        kakaoSearchViewModel = ViewModelProvider(this, kakaoSearchViewModelFactory)
            .get(KakaoSearchViewModel::class.java)
    }

    private fun setObserverToViewModel() {
        kakaoSearchViewModel.kakaoSearchLiveData.observe(
            viewLifecycleOwner,
            Observer { searchResultList ->
                viewBinding.recyclerViewSearchResultContainer.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = KakaoSearchAdapter(searchResultList, this)
                }
            })
    }

    private fun setButtonClickListener() {
        viewBinding.imageButtonSearch.setOnClickListener(fragmentButtonClickListener)
    }

    private val fragmentButtonClickListener = View.OnClickListener {
        when (it.id) {
            R.id.imageButton_search -> {
                kakaoSearchViewModel.getSearchResults(viewBinding.editTextSearch.text.toString())
            }
        }
    }

    override fun onClickKakaoSearchItem(view: View, searchResult: KakaoSearchModel.Document) {
        when (view.id) {
            R.id.constraintLayout_itemContainer -> {
                Toast.makeText(requireContext(), "search result clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
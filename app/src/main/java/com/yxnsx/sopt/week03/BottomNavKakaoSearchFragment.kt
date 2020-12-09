package com.yxnsx.sopt.week03

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yxnsx.sopt.R
import com.yxnsx.sopt.databinding.FragmentBottomNavKakaoSearchBinding
import com.yxnsx.sopt.util.KakaoSearchItemClickListener
import com.yxnsx.sopt.week06.kakao_search_api.*


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
        viewBinding.editTextSearch.setOnEditorActionListener(editTextActionListener)
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
            viewBinding.imageButtonSearch.id -> {
                setQueryToViewModel()
            }
        }
    }

    private fun setQueryToViewModel() {
        kakaoSearchViewModel.getSearchResults(viewBinding.editTextSearch.text.toString())
        hideKeyboard()
    }

    override fun onClickKakaoSearchItem(view: View, searchResult: KakaoSearchModel.Document) {
        when (view.id) {
            R.id.constraintLayout_itemContainer -> {
                Toast.makeText(requireContext(), "search result clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val editTextActionListener = TextView.OnEditorActionListener { v, actionId, _ ->
        if (v.id == viewBinding.editTextSearch.id && actionId == EditorInfo.IME_ACTION_SEARCH) {
            setQueryToViewModel()
            return@OnEditorActionListener true
        }
        return@OnEditorActionListener false
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(viewBinding.editTextSearch.windowToken, 0)
    }
}
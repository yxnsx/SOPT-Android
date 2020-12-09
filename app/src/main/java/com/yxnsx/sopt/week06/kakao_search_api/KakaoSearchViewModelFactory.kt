package com.yxnsx.sopt.week06.kakao_search_api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@Suppress("UNCHECKED_CAST")
class KakaoSearchViewModelFactory(
    private val repository: KakaoSearchRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KakaoSearchViewModel(repository) as T
    }
}
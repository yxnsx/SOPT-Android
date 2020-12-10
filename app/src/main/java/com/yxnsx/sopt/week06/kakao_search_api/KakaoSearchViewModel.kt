package com.yxnsx.sopt.week06.kakao_search_api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yxnsx.sopt.util.Coroutines
import kotlinx.coroutines.Job


class KakaoSearchViewModel(
    private val repository: KakaoSearchRepository
) : ViewModel() {

    private lateinit var coroutineJob: Job
    private val kakaoSearchMutableLiveData = MutableLiveData<List<KakaoSearchModel.Document>>()
    val kakaoSearchLiveData: LiveData<List<KakaoSearchModel.Document>> get() = kakaoSearchMutableLiveData


    fun getSearchResults(queryInput: String) {
        coroutineJob = Coroutines.ioThenMain(
            { repository.getSearchResults(queryInput) },
            { kakaoSearchMutableLiveData.value = it?.documents }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::coroutineJob.isInitialized) coroutineJob.cancel()
    }
}
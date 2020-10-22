package com.yxnsx.sopt_1017

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileListViewModel : ViewModel() {

    var profileLiveData = MutableLiveData<ArrayList<ProfileData>>()


    init {
        profileLiveData.value = setDummyData()
    }

    private fun setDummyData(): ArrayList<ProfileData> {
        return arrayListOf(
            ProfileData(R.drawable.emoji_01, "이름", "최윤소"),
            ProfileData(R.drawable.emoji_02, "나이", "25"),
            ProfileData(R.drawable.emoji_03, "파트", "안드로이드"),
            ProfileData(R.drawable.emoji_04, "github", "https://github.com/yxnsx"),
            ProfileData(R.drawable.emoji_05, "SOPT", "www.sopt.org"),
            ProfileData(R.drawable.emoji_06, "이름", "최윤소"),
            ProfileData(R.drawable.emoji_07, "나이", "25"),
            ProfileData(R.drawable.emoji_08, "파트", "안드로이드"),
            ProfileData(R.drawable.emoji_09, "github", "https://github.com/yxnsx"),
            ProfileData(R.drawable.emoji_10, "SOPT", "www.sopt.org")
        )
    }
}
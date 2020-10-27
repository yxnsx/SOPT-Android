package com.yxnsx.sopt_1017.week_02

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yxnsx.sopt_1017.R

class ProfileListViewModel : ViewModel() {

    private lateinit var profileData: MutableList<ProfileData>
    var profileLiveData = MutableLiveData<MutableList<ProfileData>>()


    init {
        profileLiveData.value = setDummyData()
    }

    private fun setDummyData(): MutableList<ProfileData> {
        profileData = arrayListOf(
            ProfileData(
                R.drawable.emoji_01,
                "이름",
                "최윤소",
                "2020-10-23",
                "안녕하세요! 최윤소입니다.."
            ),
            ProfileData(
                R.drawable.emoji_02,
                "나이",
                "25",
                "2020-10-23",
                "나이는 25살입니다.."
            ),
            ProfileData(
                R.drawable.emoji_03,
                "파트",
                "안드로이드",
                "2020-10-23",
                "우당탕탕 안드로이드 파트입니다.."
            ),
            ProfileData(
                R.drawable.emoji_04,
                "github",
                "https://github.com/yxnsx",
                "2020-10-23",
                "깃허브 링크입니다.."
            ),
            ProfileData(
                R.drawable.emoji_05,
                "SOPT",
                "www.sopt.org",
                "2020-10-23",
                "27기 ON SOPT 열심히 하겠습니다.."
            ),
            ProfileData(
                R.drawable.emoji_06,
                "이름",
                "최윤소",
                "2020-10-23",
                "안녕하세요! 최윤소입니다.."
            ),
            ProfileData(
                R.drawable.emoji_07,
                "나이",
                "25",
                "2020-10-23",
                "나이는 25살입니다.."
            ),
            ProfileData(
                R.drawable.emoji_08,
                "파트",
                "안드로이드",
                "2020-10-23",
                "우당탕탕 안드로이드 파트입니다.."
            ),
            ProfileData(
                R.drawable.emoji_09,
                "github",
                "https://github.com/yxnsx",
                "2020-10-23",
                "깃허브 링크입니다.."
            ),
            ProfileData(
                R.drawable.emoji_10,
                "SOPT",
                "www.sopt.org",
                "2020-10-23",
                "27기 ON SOPT 열심히 하겠습니다.."
            ),
            ProfileData(
                R.drawable.emoji_01,
                "이름",
                "최윤소",
                "2020-10-23",
                "안녕하세요! 최윤소입니다.."
            ),
            ProfileData(
                R.drawable.emoji_02,
                "나이",
                "25",
                "2020-10-23",
                "나이는 25살입니다.."
            ),
            ProfileData(
                R.drawable.emoji_03,
                "파트",
                "안드로이드",
                "2020-10-23",
                "우당탕탕 안드로이드 파트입니다.."
            ),
            ProfileData(
                R.drawable.emoji_04,
                "github",
                "https://github.com/yxnsx",
                "2020-10-23",
                "깃허브 링크입니다.."
            ),
            ProfileData(
                R.drawable.emoji_05,
                "SOPT",
                "www.sopt.org",
                "2020-10-23",
                "27기 ON SOPT 열심히 하겠습니다.."
            ),
            ProfileData(
                R.drawable.emoji_06,
                "이름",
                "최윤소",
                "2020-10-23",
                "안녕하세요! 최윤소입니다.."
            ),
            ProfileData(
                R.drawable.emoji_07,
                "나이",
                "25",
                "2020-10-23",
                "나이는 25살입니다.."
            ),
            ProfileData(
                R.drawable.emoji_08,
                "파트",
                "안드로이드",
                "2020-10-23",
                "우당탕탕 안드로이드 파트입니다.."
            ),
            ProfileData(
                R.drawable.emoji_09,
                "github",
                "https://github.com/yxnsx",
                "2020-10-23",
                "깃허브 링크입니다.."
            ),
            ProfileData(
                R.drawable.emoji_10,
                "SOPT",
                "www.sopt.org",
                "2020-10-23",
                "27기 ON SOPT 열심히 하겠습니다.."
            )
        )
        return profileData
    }

    fun moveProfileItem(from: Int, to: Int) {
        if (from == to) {
            return
        }
        val fromItem = profileData.removeAt(from)
        profileData.add(to, fromItem)
    }

    fun swipeProfileItem(position: Int) {
        profileData.removeAt(position)
    }
}
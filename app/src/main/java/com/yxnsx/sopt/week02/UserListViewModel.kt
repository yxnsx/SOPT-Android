package com.yxnsx.sopt.week02

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yxnsx.sopt.R

class UserListViewModel : ViewModel() {

    private lateinit var userData: MutableList<UserData>
    var profileLiveData = MutableLiveData<MutableList<UserData>>()


    init {
        profileLiveData.value = setDummyData()
    }

    private fun setDummyData(): MutableList<UserData> {
        userData = arrayListOf(
            UserData(
                R.drawable.emoji_01,
                "이름",
                "최윤소",
                "2020-10-23",
                "안녕하세요! 최윤소입니다.."
            ),
            UserData(
                R.drawable.emoji_02,
                "나이",
                "25",
                "2020-10-23",
                "나이는 25살입니다.."
            ),
            UserData(
                R.drawable.emoji_03,
                "파트",
                "안드로이드",
                "2020-10-23",
                "우당탕탕 안드로이드 파트입니다.."
            ),
            UserData(
                R.drawable.emoji_04,
                "github",
                "https://github.com/yxnsx",
                "2020-10-23",
                "깃허브 링크입니다.."
            ),
            UserData(
                R.drawable.emoji_05,
                "SOPT",
                "www.sopt.org",
                "2020-10-23",
                "27기 ON SOPT 열심히 하겠습니다.."
            ),
            UserData(
                R.drawable.emoji_06,
                "이름",
                "최윤소",
                "2020-10-23",
                "안녕하세요! 최윤소입니다.."
            ),
            UserData(
                R.drawable.emoji_07,
                "나이",
                "25",
                "2020-10-23",
                "나이는 25살입니다.."
            ),
            UserData(
                R.drawable.emoji_08,
                "파트",
                "안드로이드",
                "2020-10-23",
                "우당탕탕 안드로이드 파트입니다.."
            ),
            UserData(
                R.drawable.emoji_09,
                "github",
                "https://github.com/yxnsx",
                "2020-10-23",
                "깃허브 링크입니다.."
            ),
            UserData(
                R.drawable.emoji_10,
                "SOPT",
                "www.sopt.org",
                "2020-10-23",
                "27기 ON SOPT 열심히 하겠습니다.."
            ),
            UserData(
                R.drawable.emoji_01,
                "이름",
                "최윤소",
                "2020-10-23",
                "안녕하세요! 최윤소입니다.."
            ),
            UserData(
                R.drawable.emoji_02,
                "나이",
                "25",
                "2020-10-23",
                "나이는 25살입니다.."
            ),
            UserData(
                R.drawable.emoji_03,
                "파트",
                "안드로이드",
                "2020-10-23",
                "우당탕탕 안드로이드 파트입니다.."
            ),
            UserData(
                R.drawable.emoji_04,
                "github",
                "https://github.com/yxnsx",
                "2020-10-23",
                "깃허브 링크입니다.."
            ),
            UserData(
                R.drawable.emoji_05,
                "SOPT",
                "www.sopt.org",
                "2020-10-23",
                "27기 ON SOPT 열심히 하겠습니다.."
            ),
            UserData(
                R.drawable.emoji_06,
                "이름",
                "최윤소",
                "2020-10-23",
                "안녕하세요! 최윤소입니다.."
            ),
            UserData(
                R.drawable.emoji_07,
                "나이",
                "25",
                "2020-10-23",
                "나이는 25살입니다.."
            ),
            UserData(
                R.drawable.emoji_08,
                "파트",
                "안드로이드",
                "2020-10-23",
                "우당탕탕 안드로이드 파트입니다.."
            ),
            UserData(
                R.drawable.emoji_09,
                "github",
                "https://github.com/yxnsx",
                "2020-10-23",
                "깃허브 링크입니다.."
            ),
            UserData(
                R.drawable.emoji_10,
                "SOPT",
                "www.sopt.org",
                "2020-10-23",
                "27기 ON SOPT 열심히 하겠습니다.."
            )
        )
        return userData
    }
}
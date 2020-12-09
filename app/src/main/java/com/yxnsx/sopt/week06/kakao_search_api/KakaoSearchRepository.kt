package com.yxnsx.sopt.week06.kakao_search_api


class KakaoSearchRepository(
    private val kakaoSearchApi: KakaoSearchApi
) : KakaoSearchRequest() {

    suspend fun getSearchResults(queryInput: String) = requestApi {
        kakaoSearchApi.getSearchResult(queryInput)
    }
}
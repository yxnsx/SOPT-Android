package com.yxnsx.sopt.week06.kakao_search_api

data class KakaoSearchModel(
    val meta: Meta,
    val documents: List<Document>
) {
    data class Meta(
        val total_count: Int,
        val pageable_count: Int,
        val is_end: Boolean
    )
    data class Document(
        val dateTime: String,
        val contents: String,
        val title: String,
        val url: String
    )
}
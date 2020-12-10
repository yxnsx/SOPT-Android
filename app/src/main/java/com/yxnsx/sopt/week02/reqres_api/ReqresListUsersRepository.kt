package com.yxnsx.sopt.week02.reqres_api


class ReqresListUsersRepository(
    private val reqresListUsersApi: ReqresListUsersApi
) : ReqresListUsersRequest() {

    suspend fun getReqresUsers() = requestApi {
        reqresListUsersApi.getReqresListUsersData()
    }
}
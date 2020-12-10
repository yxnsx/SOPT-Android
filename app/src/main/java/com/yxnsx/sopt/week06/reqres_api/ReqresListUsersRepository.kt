package com.yxnsx.sopt.week06.reqres_api


class ReqresListUsersRepository(
    private val reqresListUsersApi: ReqresListUsersApi
) : ReqresListUsersRequest() {

    suspend fun getReqresUsers() = requestApi {
        reqresListUsersApi.getReqresListUsersData()
    }
}
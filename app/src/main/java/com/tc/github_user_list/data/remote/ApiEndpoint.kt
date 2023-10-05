package com.tc.github_user_list.data.remote

import com.tc.github_user_list.BuildConfig
import com.tc.github_user_list.data.model.search.SearchItemModel
import com.tc.github_user_list.data.model.search.SearchModel
import com.tc.github_user_list.data.model.user.UserDetailModel
import com.tc.github_user_list.data.model.user.UserItemModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {
    @GET(ApiDetails.USERS_ENDPOINT)
    suspend fun getUsers(): Response<ArrayList<UserItemModel>>

    @GET(ApiDetails.SEARCH_ENDPOINT)
    suspend fun setSearch(
        @Query("q") query: String
    ): Response<SearchModel>

    @GET(ApiDetails.USER_ENDPOINT)
    suspend fun getUserDetails(@Path("username") username: String
    ): Response<UserDetailModel>

}
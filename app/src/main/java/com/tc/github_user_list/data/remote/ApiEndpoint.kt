package com.tc.github_user_list.data.remote

import com.tc.github_user_list.BuildConfig
import com.tc.github_user_list.data.model.followers.FollowersItemModel
import com.tc.github_user_list.data.model.following.FollowingItemModel
import com.tc.github_user_list.data.model.search.SearchItemModel
import com.tc.github_user_list.data.model.user.UserItemModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiEndpoint {
    @GET(ApiDetails.USERS_ENDPOINT)
    @Headers(BuildConfig.API_KEY)
    suspend fun getUsers(): Response<ArrayList<UserItemModel>>

    @GET(ApiDetails.SEARCH_ENDPOINT)
    @Headers(BuildConfig.API_KEY)
    suspend fun setSearch(
        @Query("q") query: String
    ): Call<ArrayList<SearchItemModel>>

    @GET(ApiDetails.FOLLOWERS_ENDPOINT)
    @Headers(BuildConfig.API_KEY)
    suspend fun getFollowers(
        @Query("{username}") username: String
    ): Response<ArrayList<FollowersItemModel>>

    @GET(ApiDetails.FOLLOWING_ENDPOINT)
    @Headers(BuildConfig.API_KEY)
    suspend fun getFollowing(
        @Query("{username}") username: String
    ): Response<ArrayList<FollowingItemModel>>

}
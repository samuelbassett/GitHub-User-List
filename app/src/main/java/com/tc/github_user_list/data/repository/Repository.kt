package com.tc.github_user_list.data.repository

import com.tc.github_user_list.data.model.followers.FollowersItemModel
import com.tc.github_user_list.data.model.following.FollowingItemModel
import com.tc.github_user_list.data.model.search.SearchItemModel
import com.tc.github_user_list.data.model.user.UserItemModel
import retrofit2.Call
import retrofit2.Response

interface Repository {
    suspend fun getUsers(): Response<ArrayList<UserItemModel>>

    suspend fun setSearch(q: String): Call<ArrayList<SearchItemModel>>

    suspend fun getFollowers(username: String): Response<ArrayList<FollowersItemModel>>

    suspend fun getFollowing(username: String): Response<ArrayList<FollowingItemModel>>
}
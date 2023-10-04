package com.tc.github_user_list.data.repository

import com.tc.github_user_list.data.model.followers.FollowersItemModel
import com.tc.github_user_list.data.model.following.FollowingItemModel
import com.tc.github_user_list.data.model.search.SearchItemModel
import com.tc.github_user_list.data.model.user.UserItemModel
import com.tc.github_user_list.data.remote.ApiEndpoint
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: ApiEndpoint
) : Repository {

    override suspend fun getUsers(): Response<ArrayList<UserItemModel>> {
        return service.getUsers()
    }

    override suspend fun setSearch(q: String): Call<ArrayList<SearchItemModel>> {
        return service.setSearch(q)
    }

    override suspend fun getFollowers(username: String): Response<ArrayList<FollowersItemModel>> {
        return service.getFollowers(username)
    }

    override suspend fun getFollowing(username: String): Response<ArrayList<FollowingItemModel>> {
        return service.getFollowing(username)
    }
}
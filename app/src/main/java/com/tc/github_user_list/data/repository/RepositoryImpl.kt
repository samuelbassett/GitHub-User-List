package com.tc.github_user_list.data.repository

import com.tc.github_user_list.data.model.search.SearchModel
import com.tc.github_user_list.data.model.user.UserDetailModel
import com.tc.github_user_list.data.model.user.UserItemModel
import com.tc.github_user_list.data.remote.ApiEndpoint
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: ApiEndpoint
) : Repository {

    override suspend fun getUsers(): Response<ArrayList<UserItemModel>> {
        return service.getUsers()
    }

    override suspend fun setSearch(q: String): Response<SearchModel> {
        return service.setSearch(q)
    }

    override suspend fun getUserDetails(username: String): Response<UserDetailModel> {
        return service.getUserDetails(username)
    }
}
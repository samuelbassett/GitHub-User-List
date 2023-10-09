package com.tc.github_user_list.data.repository

import com.tc.github_user_list.data.model.search.SearchModel
import com.tc.github_user_list.data.model.user.UserDetailModel
import com.tc.github_user_list.data.model.user.UserItemModel
import retrofit2.Response

interface Repository {
    suspend fun getUsers(): ArrayList<UserItemModel>

    suspend fun setSearch(q: String): SearchModel

    suspend fun getUserDetails(username: String): UserDetailModel
}
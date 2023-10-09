package com.tc.github_user_list.data.repository

import com.tc.github_user_list.data.model.search.SearchItemModel
import com.tc.github_user_list.data.model.search.SearchModel
import com.tc.github_user_list.data.model.user.FollowersItemModel
import com.tc.github_user_list.data.model.user.FollowingItemModel
import com.tc.github_user_list.data.model.user.UserDetailModel
import com.tc.github_user_list.data.model.user.UserItemModel
import com.tc.github_user_list.data.remote.ApiDetails
import com.tc.github_user_list.data.remote.ApiEndpoint

class RepositoryImplTest(apiRequest: ApiEndpoint) : Repository {

    val getUsersSuccess = arrayListOf<UserItemModel>(
        UserItemModel(login = "jimmy")
    )

    val setSearchSuccess = SearchModel(
        items = listOf<SearchItemModel>(
            SearchItemModel(login = "jimmy")
        )
    )

    val getUserDetailsSuccess = UserDetailModel(
        login = "jimmy"
    )

    val getFollowersSuccess = arrayListOf<FollowersItemModel>(
        FollowersItemModel(login = "jimmy")
    )

    val getFollowingSuccess = arrayListOf<FollowingItemModel>(
        FollowingItemModel(login = "jimmy")
    )

    override suspend fun getUsers(): ArrayList<UserItemModel> {
        return getUsersSuccess
    }

    override suspend fun setSearch(q: String): SearchModel {
        return setSearchSuccess
    }

    override suspend fun getUserDetails(username: String): UserDetailModel {
        return getUserDetailsSuccess
    }

    override suspend fun getFollowers(username: String): ArrayList<FollowersItemModel> {
        return getFollowersSuccess
    }

    override suspend fun getFollowing(username: String): ArrayList<FollowingItemModel> {
        return getFollowingSuccess
    }
}
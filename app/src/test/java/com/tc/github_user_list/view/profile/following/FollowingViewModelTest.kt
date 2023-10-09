package com.tc.github_user_list.view.profile.following

import com.tc.github_user_list.data.model.user.Followers
import com.tc.github_user_list.data.model.user.FollowersItemModel
import com.tc.github_user_list.data.model.user.FollowingItemModel
import org.junit.Assert.*

import com.tc.github_user_list.data.model.user.UserItemModel
import com.tc.github_user_list.data.repository.RepositoryImplTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import com.tc.github_user_list.data.remote.ApiEndpoint
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import io.mockk.coEvery
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

class FollowingViewModelTest {

    lateinit var repository: RepositoryImplTest
    val apiRequest: ApiEndpoint = mockk()


    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        repository = RepositoryImplTest(apiRequest)

    }

    @Test
    fun `Check response success in getFollowing`() = runTest {
        val expectedUsers = arrayListOf<FollowingItemModel>(
            FollowingItemModel(login = "jimmy")
        )
        val query = "jimmy"
        coEvery { apiRequest.getFollowing(query) } returns expectedUsers

        val actualUsers = repository.getFollowing(query)

        assertEquals(expectedUsers, actualUsers)
    }

    @Test
    fun `Check response failure on getFollowing`() = runTest {
        val expectedUsers = arrayListOf<FollowingItemModel>(
            FollowingItemModel(login = "not jimmy")
        )
        val query = "jimmy"
        coEvery { apiRequest.getFollowing(query) } returns expectedUsers

        val actualUsers = repository.getFollowing(query)

        assertNotEquals(expectedUsers, actualUsers)
    }
}
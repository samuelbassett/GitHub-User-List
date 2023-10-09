package com.tc.github_user_list.view.users

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

class UsersViewModelTest {

    lateinit var repository: RepositoryImplTest
    val apiRequest: ApiEndpoint = mockk()


    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        repository = RepositoryImplTest(apiRequest)
    }

    @Test
    fun `Check response success in getUsers`() = runTest {
        val expectedUsers = arrayListOf<UserItemModel>(
            UserItemModel(login = "jimmy")
        )
        coEvery { apiRequest.getUsers() } returns expectedUsers

        val actualUsers = repository.getUsers()

        assertEquals(expectedUsers, actualUsers)
    }

    @Test
    fun `Check response failure on getUsers`() = runTest {
        val expectedUsers = arrayListOf<UserItemModel>(
            UserItemModel(login = "not jimmy")
        )
        coEvery { apiRequest.getUsers() } returns expectedUsers

        val actualUsers = repository.getUsers()

        assertNotEquals(expectedUsers, actualUsers)
    }
}
package com.tc.github_user_list.view.profile

import com.tc.github_user_list.data.model.search.SearchItemModel
import com.tc.github_user_list.data.model.search.SearchModel
import com.tc.github_user_list.data.model.user.UserDetailModel
import com.tc.github_user_list.data.remote.ApiEndpoint
import com.tc.github_user_list.data.repository.RepositoryImplTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProfileViewModelTest {
    lateinit var repository: RepositoryImplTest
    val apiRequest: ApiEndpoint = mockk()


    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        repository = RepositoryImplTest(apiRequest)
    }

    @Test
    fun `Check response success in getUserDetails`() = runTest {
        val expectedUserDetails = UserDetailModel(
                    login = "jimmy"
        )
        val query = "jimmy"
        coEvery { apiRequest.getUserDetails(query) } returns expectedUserDetails

        val actualUserDetails = repository.getUserDetails(query)

        assertEquals(expectedUserDetails, actualUserDetails)
    }

    @Test
    fun `Check response failure on getUserDetails`() = runTest {
        val expectedUserDetails = UserDetailModel(
            login = "not jimmy"
        )
        val query = "not jimmy"
        coEvery { apiRequest.getUserDetails(query) } returns expectedUserDetails

        val actualUserDetails = repository.getUserDetails(query)

        assertNotEquals(expectedUserDetails, actualUserDetails)
    }
}
package com.tc.github_user_list.view.search

import com.tc.github_user_list.data.model.search.SearchItemModel
import com.tc.github_user_list.data.model.search.SearchModel
import com.tc.github_user_list.data.model.user.UserItemModel
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

class SearchViewModelTest {
    lateinit var repository: RepositoryImplTest
    val apiRequest: ApiEndpoint = mockk()


    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        repository = RepositoryImplTest(apiRequest)
    }

    @Test
    fun `Check response success in setSearch`() = runTest {
        val expectedSearch = SearchModel(
            items = listOf(
                SearchItemModel(
                    login = "jimmy"
                )
            )
        )
        val query = "jimmy"
        coEvery { apiRequest.setSearch(query) } returns expectedSearch

        val actualSearch = repository.setSearch(query)

        assertEquals(expectedSearch, actualSearch)
    }

    @Test
    fun `Check response failure on setSearch`() = runTest {
        val expectedSearch = SearchModel(
            items = listOf(
                SearchItemModel(
                    login = "not jimmy"
                )
            )
        )
        val query = "not jimmy"
        coEvery { apiRequest.setSearch(query) } returns expectedSearch

        val actualSearch = repository.setSearch(query)

        assertNotEquals(expectedSearch, actualSearch)
    }
}
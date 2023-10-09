package com.tc.github_user_list.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.github_user_list.data.model.search.SearchItemModel
import com.tc.github_user_list.data.model.search.SearchModel
import com.tc.github_user_list.data.model.user.UserItemModel
import com.tc.github_user_list.data.repository.Repository
import com.tc.github_user_list.util.ResponseHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    private val _searchData = MutableLiveData<ResponseHandling>()
    val searchData: LiveData<ResponseHandling> = _searchData

    fun setSearch(query: String) {
        viewModelScope.launch {
            _searchData.postValue(ResponseHandling.Loading)

            val result = repository.setSearch(query)
            if (result != null) {
                _searchData.postValue(
                    ResponseHandling.Success(
                        result ?: SearchModel()
                    )
                )
            } else {
                _searchData.postValue(ResponseHandling.Error())
            }
        }
    }
}
package com.tc.github_user_list.view.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.github_user_list.data.model.user.UserItemModel
import com.tc.github_user_list.data.repository.Repository
import com.tc.github_user_list.util.ResponseHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    private val _userData = MutableLiveData<ResponseHandling>()
    val userData: LiveData<ResponseHandling> = _userData

    fun getUsers() {
        viewModelScope.launch {
            _userData.postValue(ResponseHandling.Loading)
            val result = repository.getUsers()
            if (result.size > 0) {
                _userData.postValue(
                    ResponseHandling.Success(
                        result ?: arrayListOf<UserItemModel>()
                    )
                )
            } else {
                _userData.postValue(ResponseHandling.Error())
            }
        }
    }
}
package com.tc.github_user_list.view.profile.following

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
class FollowingViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    private val _followingData = MutableLiveData<ResponseHandling>()
    val followingData: LiveData<ResponseHandling> = _followingData

    fun getFollowing(username: String) {
        viewModelScope.launch {
            _followingData.postValue(ResponseHandling.Loading)
            val result = repository.getFollowing(username)
            if (result != null) {
                _followingData.postValue(
                    ResponseHandling.Success(
                        result ?: arrayListOf<UserItemModel>()
                    )
                )
            } else {
                _followingData.postValue(ResponseHandling.Error())
            }
        }
    }
}

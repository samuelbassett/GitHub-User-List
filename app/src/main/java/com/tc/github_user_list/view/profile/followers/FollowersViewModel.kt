package com.tc.github_user_list.view.profile.followers

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
class FollowersViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    private val _followerData = MutableLiveData<ResponseHandling>()
    val followerData: LiveData<ResponseHandling> = _followerData

    fun getFollowers(username: String) {
        viewModelScope.launch {
            _followerData.postValue(ResponseHandling.Loading)
            val result = repository.getFollowers(username)
            if (result != null) {
                _followerData.postValue(
                    ResponseHandling.Success(
                        result ?: arrayListOf<UserItemModel>()
                    )
                )
            } else {
                _followerData.postValue(ResponseHandling.Error())
            }
        }
    }
}

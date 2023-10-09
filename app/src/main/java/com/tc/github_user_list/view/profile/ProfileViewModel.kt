package com.tc.github_user_list.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.github_user_list.data.model.user.UserDetailModel
import com.tc.github_user_list.data.repository.Repository
import com.tc.github_user_list.util.ResponseHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    private val _followerData = MutableLiveData<ResponseHandling>()
    val followerData: LiveData<ResponseHandling> = _followerData

    fun getUserDetails(username: String) {
        viewModelScope.launch {
            _followerData.postValue(ResponseHandling.Loading)
            val result = repository.getUserDetails(username)
            if (result != null) {
                _followerData.postValue(
                    ResponseHandling.Success(
                        result ?: UserDetailModel()
                    )
                )
            } else {
                _followerData.postValue(ResponseHandling.Error())
            }
        }
    }

}
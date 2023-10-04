package com.tc.github_user_list.util

sealed class ResponseHandling {

    object Loading: ResponseHandling()

    class Success<T>(val result: T): ResponseHandling()

    class Error(val error: String = "Something went wrong!") : ResponseHandling()
}

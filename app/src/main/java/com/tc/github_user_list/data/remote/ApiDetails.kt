package com.tc.github_user_list.data.remote

import com.tc.github_user_list.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiDetails {
    const val baseURL = "https://api.github.com/"
    const val USERS_ENDPOINT = "users"
    const val SEARCH_ENDPOINT = "search/users"
    const val USER_ENDPOINT = "users/{username}"
    const val API_KEY = BuildConfig.API_KEY
}
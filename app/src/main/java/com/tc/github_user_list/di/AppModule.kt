package com.tc.github_user_list.di

import com.google.gson.Gson
import com.tc.github_user_list.data.remote.ApiDetails
import com.tc.github_user_list.data.remote.ApiEndpoint
import com.tc.github_user_list.data.repository.Repository
import com.tc.github_user_list.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module // Module > responsible for defining definition of injection
@InstallIn(SingletonComponent::class) // dictates the scope of injection
class AppModule {
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiDetails.baseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideApiClient(
        retrofit: Retrofit
    ): ApiEndpoint {
        return retrofit.create(ApiEndpoint::class.java )
    }

    @Provides
    fun provideRepository(
        apiClient: ApiEndpoint
    ): Repository {
        return RepositoryImpl(apiClient)
    }
}
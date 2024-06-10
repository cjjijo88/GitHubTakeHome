package com.assignment.githubtakehome.di

import android.app.Application
import com.assignment.githubtakehome.network.GitHubApiService
import com.assignment.githubtakehome.repository.UserDataRepository
import com.assignment.githubtakehome.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @Author: Jijo
 * @Date: 09-06-2024
 */
@Module
class AppModule {

    /*@Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGitHubApi(retrofit: Retrofit): GitHubApi {
        return retrofit.create(GitHubApi::class.java)
    }*/


    @Provides
    @Singleton
    fun provideApiService(): GitHubApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(apiService: GitHubApiService): UserDataRepository {
        return UserDataRepository(apiService)
    }
}

package com.assignment.githubtakehome.network

import com.assignment.githubtakehome.model.RepoData
import com.assignment.githubtakehome.model.UserData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author: Jijo
 * @Date: 09-06-2024
 */
interface GitHubApiService {

    @GET("users/{userId}")
    suspend fun getUserData(@Path("userId") userId: String): Response<UserData>

    @GET("users/{userId}/repos")
    suspend fun getUserRepoDetails(@Path("userId") userId: String): Response<List<RepoData>>
}
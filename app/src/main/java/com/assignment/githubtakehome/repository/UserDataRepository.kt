package com.assignment.githubtakehome.repository

import com.assignment.githubtakehome.network.GitHubApiService
import javax.inject.Inject
import javax.inject.Singleton
/**
 * @Author: Jijo
 * @Date: 10-06-2024
 */
@Singleton
class UserDataRepository @Inject constructor(private val apiService: GitHubApiService) {

    suspend fun getUser(userId: String) = apiService.getUserData(userId)

    suspend fun getUserRepos(userId: String) = apiService.getUserRepoDetails(userId)
}
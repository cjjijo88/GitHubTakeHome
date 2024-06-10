package com.assignment.githubtakehome.ui.viewmodel

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.githubtakehome.model.RepoData
import com.assignment.githubtakehome.model.UserData
import com.assignment.githubtakehome.network.GitHubApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * @Author: Jijo
 * @Date: 09-06-2024
 */

class MainViewModel @Inject constructor(private val apiService: GitHubApiService) : ViewModel() {

    // LiveData to hold user data fetched from the API
    private val userLiveData = MutableLiveData<UserData>()
    val user: LiveData<UserData>  = userLiveData

    // LiveData to hold list of user repositories fetched from the API
    private val repoLiveData = MutableLiveData<List<RepoData>>()
    val repos: LiveData<List<RepoData>> = repoLiveData

    /**
     * This method is used to fetch user data from the API based on the user ID
     * If API call is successful, update userLiveData with the fetched user data
     * @param: userId is a String type
     */
    fun fetchUser(userId: String) {
        viewModelScope.launch {
            val response = apiService.getUserData(userId)
            if (response.isSuccessful) {
                userLiveData.value = response.body()
            }
        }
    }

    /**
     * This method is use to fetch user repositories details from the API based on the user ID
     * If API call is successful, update userLiveData with the fetched user data
     * @param: userId is a String type
     */
    fun fetchUserRepos(userId: String) {
        viewModelScope.launch {
            val response = apiService.getUserRepoDetails(userId)
            if (response.isSuccessful) {
                repoLiveData.value = response.body()
                Log.d("MainViewModel 1", response.body().toString())
            }
        }
    }


    fun onRepoClicked(repo: RepoData) {
    }
}
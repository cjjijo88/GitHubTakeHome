package com.assignment.githubtakehome.model

import com.google.gson.annotations.SerializedName

/**
 * @Author: Jijo
 * @Date: 09-06-2024
 */
data class UserData(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val name: String
)

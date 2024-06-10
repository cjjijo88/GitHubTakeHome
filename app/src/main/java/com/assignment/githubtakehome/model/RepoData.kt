package com.assignment.githubtakehome.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * @Author: Jijo
 * @Date: 09-06-2024
 */
@Parcelize
data class RepoData(
    @SerializedName("name")
    val repoName: String? = "",
    val description: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("stargazers_count")
    val stargazersCount: String? = null,
    val forks: String? = null
) : Parcelable

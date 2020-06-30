package com.yogi.githubuserfinder.core.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Yogi Putra on 30/06/20.
 * Github : https://github.com/oohyugi
 */

data class GithubBaseMdl<T>(
    @SerializedName("total_count")
    val totalCount: Int = 0,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean = false,
    @SerializedName("items")
    val items: T
)
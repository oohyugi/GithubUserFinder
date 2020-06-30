package com.yogi.githubuserfinder.core.model


import com.google.gson.annotations.SerializedName

/**
 * Created by Yogi Putra on 30/06/20.
 * Github : https://github.com/oohyugi
 */

data class GithubUserMdl(
    @SerializedName("gists_url")
    val gistsUrl: String = "",
    @SerializedName("repos_url")
    val reposUrl: String = "",
    @SerializedName("following_url")
    val followingUrl: String = "",
    @SerializedName("starred_url")
    val starredUrl: String = "",
    @SerializedName("login")
    val login: String = "",
    @SerializedName("followers_url")
    val followersUrl: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String = "",
    @SerializedName("score")
    val score: Double = 0.0,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String = "",
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    @SerializedName("events_url")
    val eventsUrl: String = "",
    @SerializedName("html_url")
    val htmlUrl: String = "",
    @SerializedName("site_admin")
    val siteAdmin: Boolean = false,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("gravatar_id")
    val gravatarId: String = "",
    @SerializedName("node_id")
    val nodeId: String = "",
    @SerializedName("organizations_url")
    val organizationsUrl: String = ""
)
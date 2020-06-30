package com.yogi.githubuserfinder.data

import com.yogi.githubuserfinder.core.model.GithubBaseMdl
import com.yogi.githubuserfinder.core.model.GithubUserMdl
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Yogi Putra on 30/06/20.
 * Github : https://github.com/oohyugi
 */

interface ApiServices {

    @GET("search/users?")
    suspend fun getListUser(
        @Query("q") username: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<GithubBaseMdl<List<GithubUserMdl>>>

}
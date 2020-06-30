package com.yogi.githubuserfinder.data.source

import com.yogi.githubuserfinder.core.model.GithubBaseMdl
import com.yogi.githubuserfinder.core.model.GithubUserMdl
import com.yogi.movie.core.utils.ResultState

/**
 * Created by Yogi Putra on 30/06/20.
 * Github : https://github.com/oohyugi
 */

interface HomeSource {

    suspend fun getHome(
        username: String,
        page: Int
    ): ResultState<GithubBaseMdl<List<GithubUserMdl>>>
}


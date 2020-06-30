package com.yogi.githubuserfinder.data.source

import com.yogi.githubuserfinder.core.model.GithubBaseMdl
import com.yogi.githubuserfinder.core.model.GithubUserMdl
import com.yogi.githubuserfinder.data.ApiServices
import com.yogi.movie.core.utils.ResultState
import com.yogi.movie.core.utils.fetchState


/**
 * Created by Yogi Putra on 30/06/20.
 * Github : https://github.com/oohyugi
 */

class HomeDataSource(private val apiServices: ApiServices) : HomeSource {
    override suspend fun getHome(
        username: String,
        page: Int
    ): ResultState<GithubBaseMdl<List<GithubUserMdl>>> {
        return fetchState {
            val response = apiServices.getListUser(
                username, page, 20
            )
            if (response.isSuccessful) {
                ResultState.Success(response.body())
            } else {

                ResultState.Error(response.message())
            }
        }
    }


}
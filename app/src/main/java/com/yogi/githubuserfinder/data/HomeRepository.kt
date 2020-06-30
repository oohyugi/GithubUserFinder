package com.yogi.githubuserfinder.data

import com.yogi.githubuserfinder.core.model.GithubBaseMdl
import com.yogi.githubuserfinder.core.model.GithubUserMdl
import com.yogi.githubuserfinder.data.source.HomeDataSource
import com.yogi.movie.core.utils.ResultState


/**
 * Created by Yogi Putra on 30/06/20.
 * Github : https://github.com/oohyugi
 */

interface HomeRepository {


    suspend fun fetchListUser(
        username: String,
        page: Int
    ): ResultState<GithubBaseMdl<List<GithubUserMdl>>>

    class HomeRepositoryImpl(private val dataSource: HomeDataSource) : HomeRepository {

        override suspend fun fetchListUser(
            username: String,
            page: Int
        ): ResultState<GithubBaseMdl<List<GithubUserMdl>>> {

            return dataSource.getHome(username, page)


        }
    }


}
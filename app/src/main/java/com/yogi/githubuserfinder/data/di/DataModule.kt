package com.yogi.githubuserfinder.data.di

import com.yogi.githubuserfinder.BuildConfig
import com.yogi.githubuserfinder.data.ApiClient
import com.yogi.githubuserfinder.data.ApiServices
import com.yogi.githubuserfinder.data.source.HomeDataSource
import org.koin.dsl.module

/**
 * Created by Yogi Putra on 30/06/20.
 * Github : https://github.com/oohyugi
 */

val dataModule = module {

    fun provideApiService(baseUrl: String): ApiServices {

        return ApiClient.retrofitClient(baseUrl).create(ApiServices::class.java)
    }
    single {
        provideApiService(BuildConfig.API_URL)
    }

    fun provideHomeSource(apiServices: ApiServices): HomeDataSource {
        return HomeDataSource(apiServices)
    }

    factory {
        provideHomeSource(get())
    }


}

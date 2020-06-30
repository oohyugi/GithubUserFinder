package com.yogi.githubuserfinder.feature.home.di


import com.yogi.githubuserfinder.data.HomeRepository
import com.yogi.githubuserfinder.data.source.HomeDataSource
import com.yogi.githubuserfinder.feature.home.ui.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Yogi Putra on 30/06/20.
 * Github : https://github.com/oohyugi
 */

val homeModule = module {

    fun provideHomeRepo(homeSource: HomeDataSource): HomeRepository.HomeRepositoryImpl {
        return HomeRepository.HomeRepositoryImpl(homeSource)
    }

    factory<HomeRepository> {
        provideHomeRepo(get())
    }

    viewModel {
        HomeViewModel(get())
    }
}
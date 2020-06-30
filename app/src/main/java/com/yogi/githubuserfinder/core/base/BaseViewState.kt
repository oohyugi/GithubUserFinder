package com.yogi.githubuserfinder.core.base

/**
 * Created by Yogi Putra on 30/06/20.
 * Github : https://github.com/oohyugi
 */

sealed class BaseViewState<out R> {
    object Loading : BaseViewState<Nothing>()
    data class Error(val errorMessage: String?, val page: Int = 1) : BaseViewState<Nothing>()
    data class Success<T>(val data: T?, val isLast: Boolean? = false) : BaseViewState<T>()
}
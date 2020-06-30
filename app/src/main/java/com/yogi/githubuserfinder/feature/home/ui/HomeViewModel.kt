package com.yogi.githubuserfinder.feature.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.githubuserfinder.core.base.BaseViewState
import com.yogi.githubuserfinder.core.model.GithubUserMdl
import com.yogi.githubuserfinder.data.HomeRepository
import com.yogi.movie.core.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Yogi Putra on 30/06/20.
 * Github : https://github.com/oohyugi
 */

class HomeViewModel(val repository: HomeRepository) : ViewModel() {

    var mUsername = "tom"
    var mPage: Int = 1

    private var mlist: MutableList<GithubUserMdl> = mutableListOf()

    private val _userSearchResult = MutableLiveData<BaseViewState<List<GithubUserMdl>>>()
    var mListUserSearchResult: LiveData<BaseViewState<List<GithubUserMdl>>> = _userSearchResult


    fun searchUser(username: String) {
        mlist.clear()
        mPage = 1
        loadUser(username, mPage)
    }

    fun loadUser(username: String, page: Int) {
        mUsername = username
        _userSearchResult.value = BaseViewState.Loading
        viewModelScope.launch {
            val request = withContext(Dispatchers.IO) {
                repository.fetchListUser(username, page)
            }
            when (request) {
                is ResultState.Success -> {
                    request.data?.let {
                        mlist.addAll(it.items)
                        _userSearchResult.value = BaseViewState.Success(mlist)


                    }


                }
                is ResultState.Error -> {
                    _userSearchResult.value = BaseViewState.Error(request.errorMessage, page)

                }
            }


        }


    }


    fun loadMoreData() {

        mPage++
        loadUser(mUsername, mPage)


    }


}

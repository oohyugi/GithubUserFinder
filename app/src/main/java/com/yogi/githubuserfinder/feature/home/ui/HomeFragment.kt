package com.yogi.githubuserfinder.feature.home.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogi.githubuserfinder.R
import com.yogi.githubuserfinder.core.base.BaseViewState
import com.yogi.movie.core.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.negative_state.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Yogi Putra on 30/06/20.
 * Github : https://github.com/oohyugi
 */

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() =
            HomeFragment()
    }

    private val TAG = HomeFragment::class.java.name

    private var mPage = 0
    private val homeViewModel: HomeViewModel by viewModel()
    lateinit var muserAdapter: GithubUserListAdapter
    lateinit var scrollListener: EndlessRecyclerViewScrollListener
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUser()
        initObserver()

        lyNegativeState?.visibility = View.VISIBLE
        etSearchUser?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (v?.text.toString().isNotEmpty()) {
                    scrollListener.resetState()
                    muserAdapter.submitList(null)
                    homeViewModel.searchUser(v?.text.toString())

                } else {
                    showErrorView("User empty")
                }

            }
            false
        }
    }


    private fun initObserver() {
        homeViewModel.apply {
            mListUserSearchResult.observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is BaseViewState.Loading -> {
                        lyNegativeState?.visibility = View.GONE
                        progressbar?.visibility = View.VISIBLE

                    }
                    is BaseViewState.Success -> {

                        result.data?.let {
                            if (it.isEmpty()) {
                                showErrorView("User Empty")
                            } else {
                                muserAdapter.submitList(result.data)

                                lyNegativeState?.visibility = View.GONE

                            }
                        }
                        progressbar?.visibility = View.GONE


                    }
                    is BaseViewState.Error -> {

                        Log.d("page", result.page.toString())
                        result.errorMessage?.let {
                            if (!it.contains("rate limit") || result.page == 1) {
                                showErrorView(result.errorMessage)
                            }
                            Toast.makeText(
                                requireContext(),
                                result.errorMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        progressbar?.visibility = View.GONE


                    }
                }
            })
        }


    }

    private fun showErrorView(message: String?) {
        lyNegativeState?.visibility = View.VISIBLE
        tvNegativeState?.text = message
    }

    private fun initUser() {
        muserAdapter =
            GithubUserListAdapter(
                GithubUserListAdapter.GithubUserListAdapterListener {

                })
        val mLayoutManager = LinearLayoutManager(context)

        scrollListener = object : EndlessRecyclerViewScrollListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                homeViewModel.loadMoreData()
            }

        }
        val dividerItemDecoration = DividerItemDecoration(
            rv_user?.context,
            mLayoutManager.orientation
        )
        rv_user?.apply {
            layoutManager = mLayoutManager
            adapter = muserAdapter
            addItemDecoration(dividerItemDecoration)
            addOnScrollListener(scrollListener)

        }

    }

}

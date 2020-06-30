package com.yogi.githubuserfinder.feature.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.yogi.githubuserfinder.R
import com.yogi.githubuserfinder.core.model.GithubUserMdl

/**
 * Created by Yogi Putra on 30/06/20.
 * Github : https://github.com/oohyugi
 */

class GithubUserListAdapter(val listener: GithubUserListAdapterListener) :
    ListAdapter<GithubUserMdl, GithubUserListAdapter.ViewHolder>(
        DiffUtilsGithubUserListAdapter()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)

        holder.bind(data, listener)


    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val img = itemView.findViewById<ImageView>(R.id.iv_user)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_user)


        fun bind(
            data: GithubUserMdl?,
            listener: GithubUserListAdapterListener
        ) {

            tvTitle.text = data?.login
            data?.avatarUrl?.let {
                img.load(it)
            }

            itemView.setOnClickListener {
                listener.onItemClickListener(data)
            }

        }

    }

    class DiffUtilsGithubUserListAdapter : DiffUtil.ItemCallback<GithubUserMdl>() {
        override fun areItemsTheSame(oldItem: GithubUserMdl, newItem: GithubUserMdl): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GithubUserMdl, newItem: GithubUserMdl): Boolean {
            return oldItem == newItem

        }

    }

    class GithubUserListAdapterListener(val clickListener: (item: GithubUserMdl?) -> Unit) {
        fun onItemClickListener(item: GithubUserMdl?) = clickListener(item)
    }


}
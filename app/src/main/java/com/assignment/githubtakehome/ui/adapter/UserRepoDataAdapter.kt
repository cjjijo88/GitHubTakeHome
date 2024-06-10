package com.assignment.githubtakehome.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.githubtakehome.R
import com.assignment.githubtakehome.databinding.ItemUserRepoBinding
import com.assignment.githubtakehome.model.RepoData
import javax.inject.Inject

/**
 * @Author: Jijo
 * @Date: 09-06-2024
 *
 * RecyclerView adapter for displaying a list of user repositories.
 *
 * @property repos List of repository data to be displayed.
 * @property clickListener Listener for item click events.
 */

class UserRepoDataAdapter @Inject constructor(
    private var repos: List<RepoData>,
    private val clickListener: (RepoData) -> Unit
) : RecyclerView.Adapter<UserRepoDataAdapter.RepoViewHolder>() {

    /**
     * This method is for create a new ViewHolder instance by inflating the item layout.
     *
     * @param parent The parent view group.
     * @param viewType The type of the view.
     * @return A new RepoViewHolder instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemUserRepoBinding = ItemUserRepoBinding.inflate(inflater, parent, false)
        return RepoViewHolder(binding)
    }

    /**
     * This method for Bind data to the ViewHolder.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    /**
     * This method used to get the total number of items in the list.
     *
     * @return The total number of items.
     */
    override fun getItemCount(): Int = repos.size

    /**
     * This inner ViewHolder class for representing each item in the RecyclerView.
     *
     * @param binding The binding object associated with the item layout.
     */
    inner class RepoViewHolder(private val binding: ItemUserRepoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: RepoData) {
            binding.repo = repo
            binding.root.setOnClickListener { clickListener(repo) }
            binding.executePendingBindings()
        }
    }


    /**
     * This method used to update the list of repositories and notify the adapter of the changes.
     *
     * @param newRepos The new list of repositories to be displayed.
     */
    fun updateRepos(newRepos: List<RepoData>) {
        repos = newRepos
        notifyDataSetChanged()
    }
}


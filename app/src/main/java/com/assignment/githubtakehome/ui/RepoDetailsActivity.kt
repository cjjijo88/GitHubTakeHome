package com.assignment.githubtakehome.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.assignment.githubtakehome.R
import com.assignment.githubtakehome.databinding.ActivityRepoDetailsBinding
import com.assignment.githubtakehome.model.RepoData
import com.assignment.githubtakehome.utils.Constants


class RepoDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_details)

        supportActionBar?.title = resources.getString(R.string.repo_details);

        val repo = intent.getParcelableExtra<RepoData>(Constants.REPO)
        binding.repo = repo;
        initViews()
    }

    /**
     * This method is used to initialize views.
     */
    @SuppressLint("ResourceAsColor")
    private fun initViews() {
        binding.tvRepoTitle.isSelected = true
        binding.tvForksCount.isSelected = true
        binding.tvRepoDesc.movementMethod = ScrollingMovementMethod()
        updateForkCountTextColor()
    }

    /**
     * This method is used to change fork count text color if necessary.
     */
    private fun updateForkCountTextColor() {
        // Check a condition: if forks count is not null or empty and greater than 5000
        // if greater than 5000 then switch color
        if (!binding.repo?.forks.toString().isNullOrEmpty()
            && binding.repo?.forks?.toInt()!! > 5000) { //
            val color = ContextCompat.getColor(this, R.color.red_01)
            binding.tvForksCount.setTextColor(color)
        }
    }
}
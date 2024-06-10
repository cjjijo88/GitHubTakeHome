package com.assignment.githubtakehome.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.githubtakehome.MyApp
import com.assignment.githubtakehome.R
import com.assignment.githubtakehome.databinding.ActivityMainBinding
import com.assignment.githubtakehome.ui.adapter.UserRepoDataAdapter
import com.assignment.githubtakehome.ui.viewmodel.ViewModelFactory
import com.assignment.githubtakehome.ui.viewmodel.MainViewModel
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject


class MainActivity :  AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private lateinit var repoAdapter: UserRepoDataAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.btnFetchUser.setOnClickListener {
            val userId = binding.etSearch.text.toString()
            if (userId.isNotEmpty()) {
                viewModel.fetchUser(userId)
                viewModel.fetchUserRepos(userId)
                binding.etSearch.hideKeyboard()
            } else {
                Toast.makeText(this, resources.getString(R.string.toast_enter_user_id), Toast.LENGTH_LONG).show()
            }
        }

        binding.etSearch?.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                animateHint(binding.textInputLayout)
            }
        }

        setRecyclerView();
        setupObservers();
    }

    /**
     * This method is used to initialize recyclerview and adapter
     */
    private fun setRecyclerView() {
        repoAdapter = UserRepoDataAdapter(emptyList()) { repo ->
            val intent = Intent(this, RepoDetailsActivity::class.java)
            intent.putExtra("repo", repo)
            startActivity(intent)
        }
        binding.rvRepoData.layoutManager = LinearLayoutManager(this)
        binding.rvRepoData.adapter = repoAdapter

        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom)
        binding.rvRepoData.animation = animation
    }

    /**
     * This method is used to setup observers for livedata
     */
    private fun setupObservers() {
        viewModel.repos.observe(this, Observer { repos ->
            repoAdapter.updateRepos(repos) // Update adapter with new repository data
        })
    }

    /**
     * This extension function is used to hide keyboard
     */
    @SuppressLint("ServiceCast")
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    /**
     * This method is used to animate hint text in TextInputLayout
     */
    private fun animateHint(txtInput: TextInputLayout) {
        txtInput.hint = resources.getString(R.string.hint_enter_user_id)
        txtInput.setHintTextAppearance(R.style.HintTextAppearance)
    }
}

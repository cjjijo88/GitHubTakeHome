package com.assignment.githubtakehome.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * @Author: Jijo
 * @Date: 09-06-2024
 */
@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModelProviders: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    /**
     * This method Create an instance of the specified ViewModel class.
     *
     * @param modelClass The class of the ViewModel to be created.
     * @return An instance of the ViewModel.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelProviders[modelClass]?.get() as T
    }
}

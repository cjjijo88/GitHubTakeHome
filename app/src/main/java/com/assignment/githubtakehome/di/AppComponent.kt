package com.assignment.githubtakehome.di

import android.content.Context
import com.assignment.githubtakehome.ui.MainActivity
import com.assignment.githubtakehome.ui.RepoDetailsActivity
import com.assignment.githubtakehome.ui.viewmodel.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * @Author: Jijo
 * @Date: 09-06-2024
 */

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    //fun inject(viewModel: MainViewModel)
    //fun inject(activity: RepoDetailsActivity)
}
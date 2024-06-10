package com.assignment.githubtakehome

import com.assignment.githubtakehome.di.AppComponent
import com.assignment.githubtakehome.di.DaggerAppComponent

/**
 * @Author: Jijo
 * @Date: 10-06-2024
 */
import android.app.Application


class MyApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}

package com.dicoding.ariefaryudisyidik.githubuser

import android.app.Application
import com.dicoding.ariefaryudisyidik.githubuser.di.databaseModule
import com.dicoding.ariefaryudisyidik.githubuser.di.networkModule
import com.dicoding.ariefaryudisyidik.githubuser.di.repositoryModule
import com.dicoding.ariefaryudisyidik.githubuser.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    networkModule,
                    databaseModule
                )
            )
        }
    }
}
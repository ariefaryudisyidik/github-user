package com.dicoding.ariefaryudisyidik.githubuser

import android.app.Application
import com.dicoding.ariefaryudisyidik.githubuser.di.*
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
                    preferencesModule,
                    repositoryModule,
                    networkModule,
                    databaseModule
                )
            )
        }
    }
}
package com.dicoding.ariefaryudisyidik.githubuser.di

import androidx.room.Room
import com.dicoding.ariefaryudisyidik.githubuser.BuildConfig
import com.dicoding.ariefaryudisyidik.githubuser.data.UserRepository
import com.dicoding.ariefaryudisyidik.githubuser.data.local.room.UserDatabase
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.retrofit.ApiConfig
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.retrofit.ApiService
import com.dicoding.ariefaryudisyidik.githubuser.ui.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    factory { UserRepository(get(), get()) }
}

val networkModule = module {
    single { ApiConfig.getApiService() }
}

val databaseModule = module {
    factory { get<UserDatabase>().userDao }
    single {
        Room.databaseBuilder(
            androidContext(), UserDatabase::class.java,
            "User.db"
        ).allowMainThreadQueries().build()
    }
}



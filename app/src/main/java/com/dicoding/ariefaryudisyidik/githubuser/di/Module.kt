package com.dicoding.ariefaryudisyidik.githubuser.di

import androidx.room.Room
import com.dicoding.ariefaryudisyidik.githubuser.data.UserRepository
import com.dicoding.ariefaryudisyidik.githubuser.data.local.room.UserDatabase
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.retrofit.ApiConfig
import com.dicoding.ariefaryudisyidik.githubuser.ui.detail.DetailViewModel
import com.dicoding.ariefaryudisyidik.githubuser.ui.favorite.FavoriteViewModel
import com.dicoding.ariefaryudisyidik.githubuser.ui.followers.FollowersViewModel
import com.dicoding.ariefaryudisyidik.githubuser.ui.following.FollowingViewModel
import com.dicoding.ariefaryudisyidik.githubuser.ui.main.MainViewModel
import com.dicoding.ariefaryudisyidik.githubuser.utils.ThemePreferences
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FollowersViewModel(get()) }
    viewModel { FollowingViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}

val preferencesModule = module {
    single { ThemePreferences(get()) }
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
            androidContext(),
            UserDatabase::class.java,
            "User.db"
        ).allowMainThreadQueries().build()
    }
}



package com.dicoding.ariefaryudisyidik.githubuser.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ariefaryudisyidik.githubuser.R
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ActivityMainBinding
import com.dicoding.ariefaryudisyidik.githubuser.ui.detail.DetailActivity
import com.dicoding.ariefaryudisyidik.githubuser.ui.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.isLoading.value }
        }
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list.addAll(listUsers)
        showListUser()
    }

    private val listUsers: ArrayList<User>
        @SuppressLint("Recycle")
        get() {
            resources.apply {
                val username = getStringArray(R.array.username)
                val name = getStringArray(R.array.name)
                val avatar = obtainTypedArray(R.array.avatar)
                val company = getStringArray(R.array.company)
                val location = getStringArray(R.array.location)
                val repository = getStringArray(R.array.repository)
                val followers = getStringArray(R.array.followers)
                val following = getStringArray(R.array.following)
                val listUser = ArrayList<User>()
                for (i in username.indices) {
                    val user = User(
                        username[i],
                        name[i],
                        avatar.getResourceId(i, -1),
                        company[i],
                        location[i],
                        repository[i].toInt(),
                        followers[i].toInt(),
                        following[i].toInt()
                    )
                    listUser.add(user)
                }
                return listUser
            }
        }

    private fun showListUser() {
        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = MainAdapter(list)
        }
    }
}
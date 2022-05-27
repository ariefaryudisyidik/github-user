package com.dicoding.ariefaryudisyidik.githubuser.ui.main

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.User
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.isLoading.value }
        }
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.progressBar.observe(this) { showLoading(it) }
        viewModel.user.observe(this) { showListUser(it) }
        searchAction()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun searchAction() {
        binding.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.searchUsers(query)
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
        }
    }

    private fun showListUser(list: List<User>) {
        binding.apply {
            val listMainAdapter = MainAdapter(list)
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = listMainAdapter
//            listMainAdapter.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback {
//                override fun onItemClicked(data: Items) {
//                    searchView.clearFocus()
//                    val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
//                    intentToDetail.putExtra(DetailActivity.EXTRA_USER, data)
//                    startActivity(intentToDetail)
//                }
//            })
        }
    }
}
package com.dicoding.ariefaryudisyidik.githubuser.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ariefaryudisyidik.githubuser.R
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.Items
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ActivityMainBinding
import com.dicoding.ariefaryudisyidik.githubuser.ui.detail.DetailActivity

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
        viewModel.users.observe(this) { showUser(it) }
        searchAction()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
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

    private fun showUser(list: List<Items>) {
        binding.apply {
            lottieAnimationView.visibility = View.GONE
            if (list.isEmpty()) {
                rvUser.visibility = View.GONE
                layoutEmpty.root.visibility = View.VISIBLE
            } else {
                rvUser.visibility = View.VISIBLE
                layoutEmpty.root.visibility = View.GONE
                val mainAdapter = MainAdapter(list)
                rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
                rvUser.setHasFixedSize(true)
                rvUser.adapter = mainAdapter
                mainAdapter.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: Items) {
                        searchView.clearFocus()
                        val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                        detailIntent.putExtra(DetailActivity.EXTRA_USERNAME, data.login)
                        startActivity(detailIntent)
                    }
                })
            }
        }
    }
}
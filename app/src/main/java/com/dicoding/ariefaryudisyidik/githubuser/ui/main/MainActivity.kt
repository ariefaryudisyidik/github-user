package com.dicoding.ariefaryudisyidik.githubuser.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ariefaryudisyidik.githubuser.R
import com.dicoding.ariefaryudisyidik.githubuser.data.Result
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ActivityMainBinding
import com.dicoding.ariefaryudisyidik.githubuser.ui.favorite.FavoriteActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.isLoading.value }
        }
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchAction()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.theme_menu -> {}
            R.id.favorite_menu -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
            }
        }
        return true
    }

    private fun searchAction() {
        binding.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(username: String): Boolean {
                    viewModel.searchUser(username).observe(this@MainActivity) { result ->
                        when (result) {
                            is Result.Loading -> progressBar.visibility = View.VISIBLE
                            is Result.Success -> {
                                progressBar.visibility = View.GONE
                                val data = result.data
                                Log.d("TAG", "onQueryTextSubmit: $data")
                                lottieAnimationView.visibility = View.GONE
                                if (data.isEmpty()) {
                                    rvUser.visibility = View.GONE
                                    layoutEmpty.root.visibility = View.VISIBLE
                                } else {
                                    rvUser.visibility = View.VISIBLE
                                    layoutEmpty.root.visibility = View.GONE
                                    val mainAdapter = MainAdapter(data)
                                    rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
                                    rvUser.setHasFixedSize(true)
                                    rvUser.adapter = mainAdapter
                                }

                            }
                            is Result.Error -> {
                                progressBar.visibility = View.GONE
                                Toast.makeText(
                                    this@MainActivity,
                                    "Something wrong",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
        }
    }
}
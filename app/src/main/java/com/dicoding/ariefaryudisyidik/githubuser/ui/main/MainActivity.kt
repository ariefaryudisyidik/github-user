package com.dicoding.ariefaryudisyidik.githubuser.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ariefaryudisyidik.githubuser.R
import com.dicoding.ariefaryudisyidik.githubuser.data.Result
import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ActivityMainBinding
import com.dicoding.ariefaryudisyidik.githubuser.ui.favorite.FavoriteActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private var lightMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.isLoading.value }
        }
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTheme()
        searchAction()
        getUser()
    }

    private fun setupTheme() {
        viewModel.getThemeMode().observe(this) { isNightModeActive ->
            lightMode = if (isNightModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                false
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        if (lightMode) {
            menu.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_night_mode)
        } else {
            menu.getItem(0).icon = ContextCompat.getDrawable(this, R.drawable.ic_light_mode)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_theme -> {
                binding.searchView.clearFocus()
                viewModel.saveThemeMode(lightMode)
            }
            R.id.menu_favorite -> {
                binding.searchView.clearFocus()
                startActivity(Intent(this, FavoriteActivity::class.java))
            }
        }
        return true
    }

    private fun searchAction() {
        binding.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(username: String): Boolean {
                    viewModel.searchUser(username)
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
        }
    }

    private fun getUser() {
        viewModel.users.observe(this) { showUser(it) }
    }

    private fun showUser(result: Result<List<UserEntity>>) {
        binding.apply {
            when (result) {
                is Result.Loading -> progressBar.visibility = View.VISIBLE
                is Result.Success -> {
                    progressBar.visibility = View.GONE
                    lottieAnimationView.visibility = View.GONE
                    val data = result.data
                    if (data.isEmpty()) {
                        layoutEmpty.root.visibility = View.VISIBLE
                        rvUser.visibility = View.GONE
                    } else {
                        val mainAdapter = MainAdapter(data)
                        mainAdapter.submitList(data)
                        layoutEmpty.root.visibility = View.GONE
                        rvUser.visibility = View.VISIBLE
                        rvUser.setHasFixedSize(true)
                        rvUser.adapter = mainAdapter
                    }
                }
                is Result.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, result.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
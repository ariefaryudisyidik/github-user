package com.dicoding.ariefaryudisyidik.githubuser.ui.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ariefaryudisyidik.githubuser.data.Result
import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ActivityFavoriteBinding
import com.dicoding.ariefaryudisyidik.githubuser.ui.main.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel by viewModel<FavoriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getFavoriteUser().observe(this) { showFavoriteUser(it) }
    }

    private fun showFavoriteUser(result: Result<List<UserEntity>>) {
        binding.apply {
            when (result) {
                is Result.Loading -> progressBar.visibility = View.VISIBLE
                is Result.Success -> {
                    progressBar.visibility = View.GONE
                    val data = result.data
                    if (data.isEmpty()) {
                        layoutEmpty.root.visibility = View.VISIBLE
                        rvFavoriteUser.visibility = View.GONE
                    } else {
                        val mainAdapter = MainAdapter(data)
                        mainAdapter.submitList(data)
                        layoutEmpty.root.visibility = View.GONE
                        rvFavoriteUser.visibility = View.VISIBLE
                        rvFavoriteUser.layoutManager = LinearLayoutManager(this@FavoriteActivity)
                        rvFavoriteUser.setHasFixedSize(true)
                        rvFavoriteUser.adapter = mainAdapter
                    }
                }
                is Result.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@FavoriteActivity, result.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
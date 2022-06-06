package com.dicoding.ariefaryudisyidik.githubuser.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
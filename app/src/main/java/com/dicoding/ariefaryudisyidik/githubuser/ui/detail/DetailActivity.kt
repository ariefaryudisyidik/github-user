package com.dicoding.ariefaryudisyidik.githubuser.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ActivityDetailBinding
import com.dicoding.ariefaryudisyidik.githubuser.model.User

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showUserDetails()
    }

    private fun showUserDetails() {
        binding.apply {
            val user = intent.getParcelableExtra<User>(EXTRA_USER)
            if (user != null) {
//                civProfile.setImageResource(user.avatar)
//                tvName.text = user.name
                tvUsername.text = user.username
                tvRepository.text = StringBuilder("${user.repository}\nRepository")
                tvFollowers.text = StringBuilder("${user.followers}\nFollowers")
                tvFollowing.text = StringBuilder("${user.following}\nFollowing")
//                tvCompany.text = user.company
//                tvLocation.text = user.location
            }
        }
    }
}
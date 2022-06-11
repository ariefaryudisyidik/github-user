package com.dicoding.ariefaryudisyidik.githubuser.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.ariefaryudisyidik.githubuser.R
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ActivityDetailBinding
import com.dicoding.ariefaryudisyidik.githubuser.model.User

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showUserDetails()
    }

    private fun showUserDetails() {
        binding.apply {
            val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
            Glide.with(this@DetailActivity)
                .load(user.avatar)
                .circleCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivProfile)
            tvName.text = user.name
            tvUsername.text = user.username
            tvRepository.text = resources.getString(R.string.data_repository, user.repository)
            tvFollowers.text = resources.getString(R.string.data_followers, user.followers)
            tvFollowing.text = resources.getString(R.string.data_following, user.following)
            tvCompany.text = user.company
            tvLocation.text = user.location
        }
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}
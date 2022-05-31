package com.dicoding.ariefaryudisyidik.githubuser.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.ariefaryudisyidik.githubuser.R
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    companion object {
        const val EXTRA_USERNAME = "extra_username"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showUserDetails()
        tabSetup()
    }

    private fun showUserDetails() {
        binding.apply {
            val username = intent.getStringExtra(EXTRA_USERNAME)
            if (username != null) {
                viewModel.setUserDetails(username)
            }
            viewModel.userDetails.observe(this@DetailActivity) { user ->
                Glide.with(this@DetailActivity)
                    .load(user.avatarUrl)
                    .circleCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivProfile)
                tvFullName.text = user.name
                tvUsername.text = user.login
                tvRepository.text = StringBuilder("${user.publicRepos}\nRepository")
                tvFollowers.text = StringBuilder("${user.followers}\nFollowers")
                tvFollowing.text = StringBuilder("${user.following}\nFollowing")
            }
        }
    }

    private fun tabSetup() {
        val sectionPagerAdapter = SectionPagerAdapter(this)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }
}
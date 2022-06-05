package com.dicoding.ariefaryudisyidik.githubuser.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.ariefaryudisyidik.githubuser.R
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private val mBundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showUserDetails()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showUserDetails() {
        viewModel.progressBar.observe(this) { showLoading(it) }
        binding.apply {
            val username = intent.getStringExtra(EXTRA_USERNAME).toString()
            mBundle.putString(EXTRA_USERNAME, username)

            viewModel.setUserDetails(username)
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
                tabSetup()
            }
        }
    }

    private fun tabSetup() {
        val sectionPagerAdapter = SectionPagerAdapter(this, mBundle)
        binding.apply {
            tabLayout.visibility = View.VISIBLE
            viewPager.adapter = sectionPagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}
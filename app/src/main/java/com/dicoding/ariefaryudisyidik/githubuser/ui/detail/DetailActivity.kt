package com.dicoding.ariefaryudisyidik.githubuser.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.ariefaryudisyidik.githubuser.R
import com.dicoding.ariefaryudisyidik.githubuser.data.Result
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.UserDetailsResponse
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModel<DetailViewModel>()
    private val mBundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showUserDetails()
    }

    private fun showUserDetails() {
        binding.apply {
            val username = intent.getStringExtra(EXTRA_USERNAME).toString()
            mBundle.putString(EXTRA_USERNAME, username)
            viewModel.setUserDetails(username).observe(this@DetailActivity) { getUserDetails(it) }
        }
    }

    private fun getUserDetails(result: Result<UserDetailsResponse>) {
        binding.apply {
            when (result) {
                is Result.Loading -> progressBar.visibility = View.VISIBLE
                is Result.Success -> {
                    progressBar.visibility = View.GONE
                    val data = result.data
                    Glide.with(this@DetailActivity)
                        .load(data.avatarUrl)
                        .circleCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(ivProfile)
                    tvFullName.text = data.name
                    tvUsername.text = data.login
                    tvRepository.text = StringBuilder("${data.publicRepos}\nRepository")
                    tvFollowers.text = StringBuilder("${data.followers}\nFollowers")
                    tvFollowing.text = StringBuilder("${data.following}\nFollowing")
                    tabSetup()
                }
                is Result.Error -> {
                    progressBar.visibility = View.GONE
                    result.error.let { Log.e("DetailActivity", "showUserDetails: $it") }
                }
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
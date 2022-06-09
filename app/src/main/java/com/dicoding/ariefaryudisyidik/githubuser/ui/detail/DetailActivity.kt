package com.dicoding.ariefaryudisyidik.githubuser.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.ariefaryudisyidik.githubuser.R
import com.dicoding.ariefaryudisyidik.githubuser.data.Result
import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity
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
            viewModel.getUserDetails(username).observe(this@DetailActivity) { setUserDetails(it) }
            mBundle.putString(EXTRA_USERNAME, username)
        }
    }

    private fun setUserDetails(result: Result<UserDetailsResponse>) {
        binding.apply {
            when (result) {
                is Result.Loading -> progressBar.visibility = View.VISIBLE
                is Result.Success -> {
                    progressBar.visibility = View.GONE
                    fabFavorite.visibility = View.VISIBLE
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
                    favoriteClick()
                }
                is Result.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@DetailActivity, result.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun favoriteClick() {
        binding.apply {
            val user = intent.getParcelableExtra<UserEntity>(EXTRA_USER) as UserEntity
            val favorite = viewModel.checkUser(user.login)
            var isFavorite = if (favorite > 0) {
                fabFavorite.setColorFilter(
                    ContextCompat.getColor(this@DetailActivity, R.color.red)
                )
                true
            } else {
                fabFavorite.setColorFilter(
                    ContextCompat.getColor(this@DetailActivity, R.color.black)
                )
                false
            }

            fabFavorite.setOnClickListener {
                isFavorite = if (isFavorite) {
                    viewModel.deleteFavoriteUser(user.login)
                    fabFavorite.setColorFilter(
                        ContextCompat.getColor(this@DetailActivity, R.color.black)
                    )
                    false
                } else {
                    viewModel.addFavoriteUser(user)
                    fabFavorite.setColorFilter(
                        ContextCompat.getColor(this@DetailActivity, R.color.red)
                    )
                    true
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
        const val EXTRA_USER = "extra_user"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}
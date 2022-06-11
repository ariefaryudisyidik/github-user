package com.dicoding.ariefaryudisyidik.githubuser.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.ariefaryudisyidik.githubuser.R
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showAbout()
    }

    private fun showAbout() {
        binding.apply {
            Glide.with(this@AboutActivity)
                .load(R.drawable.arief_aryudi_syidik)
                .circleCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivProfile)
        }
    }
}
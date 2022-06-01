package com.dicoding.ariefaryudisyidik.githubuser.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.ariefaryudisyidik.githubuser.ui.followers.FollowersFragment
import com.dicoding.ariefaryudisyidik.githubuser.ui.following.FollowingFragment

class SectionPagerAdapter(activity: AppCompatActivity, data: Bundle) :
    FragmentStateAdapter(activity) {

    private var mBundle: Bundle

    init {
        mBundle = data
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.mBundle
        return fragment as Fragment
    }

    override fun getItemCount() = 2
}
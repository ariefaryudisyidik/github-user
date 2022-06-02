package com.dicoding.ariefaryudisyidik.githubuser.ui.followers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ariefaryudisyidik.githubuser.R
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.Items
import com.dicoding.ariefaryudisyidik.githubuser.databinding.FragmentFollowersBinding
import com.dicoding.ariefaryudisyidik.githubuser.ui.detail.DetailActivity
import com.dicoding.ariefaryudisyidik.githubuser.ui.main.MainAdapter

class FollowersFragment : Fragment(R.layout.fragment_followers) {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FollowersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowersBinding.bind(view)

        val username = arguments?.getString(DetailActivity.EXTRA_USERNAME).toString()
        viewModel.setListFollowers(username)
        viewModel.listFollowers.observe(viewLifecycleOwner) { showFollowers(it) }
    }

    private fun showFollowers(list: List<Items>) {
        binding.apply {
            val mainAdapter = MainAdapter(list)
            rvFollowers.layoutManager = LinearLayoutManager(requireContext())
            rvFollowers.setHasFixedSize(true)
            rvFollowers.adapter = mainAdapter
            mainAdapter.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Items) {
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
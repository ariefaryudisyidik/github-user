package com.dicoding.ariefaryudisyidik.githubuser.ui.following

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ariefaryudisyidik.githubuser.R
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.Items
import com.dicoding.ariefaryudisyidik.githubuser.databinding.FragmentFollowingBinding
import com.dicoding.ariefaryudisyidik.githubuser.ui.detail.DetailActivity
import com.dicoding.ariefaryudisyidik.githubuser.ui.main.MainAdapter

class FollowingFragment : Fragment(R.layout.fragment_following) {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FollowingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowingBinding.bind(view)

        val username = arguments?.getString(DetailActivity.EXTRA_USERNAME).toString()
        viewModel.setListFollowing(username)
        viewModel.progressBar.observe(viewLifecycleOwner) { showLoading(it) }
        viewModel.listFollowing.observe(viewLifecycleOwner) { showFollowing(it) }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showFollowing(list: List<Items>) {
        binding.apply {
            val mainAdapter = MainAdapter(list)
            rvFollowing.layoutManager = LinearLayoutManager(requireContext())
            rvFollowing.setHasFixedSize(true)
            rvFollowing.adapter = mainAdapter
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
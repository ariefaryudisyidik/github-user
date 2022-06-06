package com.dicoding.ariefaryudisyidik.githubuser.ui.followers

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ariefaryudisyidik.githubuser.R
import com.dicoding.ariefaryudisyidik.githubuser.data.Result
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.Items
import com.dicoding.ariefaryudisyidik.githubuser.databinding.FragmentFollowersBinding
import com.dicoding.ariefaryudisyidik.githubuser.ui.detail.DetailActivity
import com.dicoding.ariefaryudisyidik.githubuser.ui.main.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowersFragment : Fragment(R.layout.fragment_followers) {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<FollowersViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowersBinding.bind(view)

        showFollowers()
    }

    private fun showFollowers() {
        val username = arguments?.getString(DetailActivity.EXTRA_USERNAME).toString()
        viewModel.getFollowers(username).observe(viewLifecycleOwner) { setFollowers(it) }
    }

    private fun setFollowers(result: Result<List<Items>>) {
        binding.apply {
            when (result) {
                is Result.Loading -> {}
                is Result.Success -> {
                    val data = result.data
                    if (data.isEmpty()) {
                        layoutEmpty.root.visibility = View.VISIBLE
                        rvFollowers.visibility = View.GONE
                    } else {
                        val mainAdapter = MainAdapter(data)
                        layoutEmpty.root.visibility = View.GONE
                        rvFollowers.visibility = View.VISIBLE
                        rvFollowers.layoutManager = LinearLayoutManager(requireContext())
                        rvFollowers.setHasFixedSize(true)
                        rvFollowers.adapter = mainAdapter
                    }
                }
                is Result.Error -> {
                    Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
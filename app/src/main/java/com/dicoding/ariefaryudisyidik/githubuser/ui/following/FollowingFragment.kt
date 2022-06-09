package com.dicoding.ariefaryudisyidik.githubuser.ui.following

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ariefaryudisyidik.githubuser.R
import com.dicoding.ariefaryudisyidik.githubuser.data.Result
import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity
import com.dicoding.ariefaryudisyidik.githubuser.databinding.FragmentFollowingBinding
import com.dicoding.ariefaryudisyidik.githubuser.ui.detail.DetailActivity
import com.dicoding.ariefaryudisyidik.githubuser.ui.main.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowingFragment : Fragment(R.layout.fragment_following) {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<FollowingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowingBinding.bind(view)

        showFollowing()
    }

    private fun showFollowing() {
        val username = arguments?.getString(DetailActivity.EXTRA_USERNAME).toString()
        viewModel.getFollowing(username).observe(viewLifecycleOwner) { setFollowing(it) }
    }

    private fun setFollowing(result: Result<List<UserEntity>>) {
        binding.apply {
            when (result) {
                is Result.Loading -> progressBar.visibility = View.VISIBLE
                is Result.Success -> {
                    progressBar.visibility = View.GONE
                    val data = result.data
                    if (data.isEmpty()) {
                        layoutEmpty.root.visibility = View.VISIBLE
                        rvFollowing.visibility = View.GONE
                    } else {
                        val mainAdapter = MainAdapter(data)
                        mainAdapter.submitList(data)
                        layoutEmpty.root.visibility = View.GONE
                        rvFollowing.visibility = View.VISIBLE
                        rvFollowing.layoutManager = LinearLayoutManager(requireContext())
                        rvFollowing.setHasFixedSize(true)
                        rvFollowing.adapter = mainAdapter
                    }
                }
                is Result.Error -> {
                    progressBar.visibility = View.GONE
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
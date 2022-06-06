package com.dicoding.ariefaryudisyidik.githubuser.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.ariefaryudisyidik.githubuser.data.local.entity.UserEntity
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.Items
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ItemUserBinding
import com.dicoding.ariefaryudisyidik.githubuser.ui.detail.DetailActivity

class MainAdapter(private val listItems: List<Items>) :
    ListAdapter<Items, MainAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount() = listItems.size

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: Items) {
            with(binding) {
                Glide.with(itemView)
                    .load(items.avatarUrl)
                    .circleCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivProfile)
                tvUsername.text = items.login
                tvUserUrl.text = items.userUrl
                root.setOnClickListener {
                    val detailIntent = Intent(itemView.context, DetailActivity::class.java)
                    detailIntent.putExtra(DetailActivity.EXTRA_USERNAME, items.login)
                    itemView.context.startActivity(detailIntent)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Items> =
            object : DiffUtil.ItemCallback<Items>() {
                override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
                    return oldItem.login == newItem.login
                }

                override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
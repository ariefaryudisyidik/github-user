package com.dicoding.ariefaryudisyidik.githubuser.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.ariefaryudisyidik.githubuser.databinding.ItemUserBinding
import com.dicoding.ariefaryudisyidik.githubuser.model.User

class MainAdapter(private val listUsers: ArrayList<User>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listUsers[position])
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUsers[position]) }
    }

    override fun getItemCount() = listUsers.size

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                civProfile.setImageResource(user.avatar)
                tvName.text = user.name
                tvUsername.text = user.username
                tvRepository.text = StringBuilder("${user.repository} Repository")
                cvItemUser.setOnClickListener {
                }
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}
package com.dicoding.ariefaryudisyidik.githubuser.ui.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.Items
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    private val _listFollowing = MutableLiveData<List<Items>>()
    val listFollowing: LiveData<List<Items>> = _listFollowing

    companion object {
        private const val TAG = "FollowingViewModel"
    }

    fun setListFollowing(username: String) {
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<Items>> {
            override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
                if (response.isSuccessful) {
                    _listFollowing.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Items>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}
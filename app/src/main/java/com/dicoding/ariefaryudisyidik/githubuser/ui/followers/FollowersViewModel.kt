package com.dicoding.ariefaryudisyidik.githubuser.ui.followers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.Items

class FollowersViewModel : ViewModel() {

    private val _listFollowers = MutableLiveData<List<Items>>()
    val listFollowers: LiveData<List<Items>> = _listFollowers

//    fun setListFollowers(username: String) {
//        val client = ApiConfig.getApiService().getFollowers(username)
//        client.enqueue(object : Callback<List<Items>> {
//            override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
//                if (response.isSuccessful) {
//                    _listFollowers.postValue(response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<List<Items>>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }

    companion object {
        private const val TAG = "FollowersViewModel"
    }
}
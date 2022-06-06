package com.dicoding.ariefaryudisyidik.githubuser.ui.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.Items

class FollowingViewModel : ViewModel() {

    private val _listFollowing = MutableLiveData<List<Items>>()
    val listFollowing: LiveData<List<Items>> = _listFollowing

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> = _progressBar

//    fun setListFollowing(username: String) {
//        val client = ApiConfig.getApiService().getFollowing(username)
//        client.enqueue(object : Callback<List<Items>> {
//            override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
//                _progressBar.value = false
//                if (response.isSuccessful) {
//                    _listFollowing.postValue(response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<List<Items>>, t: Throwable) {
//                _progressBar.value = false
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }

    companion object {
        private const val TAG = "FollowingViewModel"
    }
}
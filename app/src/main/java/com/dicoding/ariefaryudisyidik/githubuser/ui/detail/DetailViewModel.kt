package com.dicoding.ariefaryudisyidik.githubuser.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.ariefaryudisyidik.githubuser.data.remote.response.UserDetailsResponse

class DetailViewModel : ViewModel() {

    private val _userDetails = MutableLiveData<UserDetailsResponse>()
    val userDetails: LiveData<UserDetailsResponse> = _userDetails

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> = _progressBar

//    fun setUserDetails(username: String) {
//        val client = ApiConfig.getApiService().getUserDetails(username)
//        client.enqueue(object : Callback<UserDetailsResponse> {
//            override fun onResponse(
//                call: Call<UserDetailsResponse>,
//                response: Response<UserDetailsResponse>
//            ) {
//                if (response.isSuccessful) {
//                    _progressBar.value = false
//                    _userDetails.postValue(response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<UserDetailsResponse>, t: Throwable) {
//                _progressBar.value = false
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }

    companion object {
        private const val TAG = "DetailViewModel"
    }
}
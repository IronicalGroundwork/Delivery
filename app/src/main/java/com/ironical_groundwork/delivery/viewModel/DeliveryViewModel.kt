package com.ironical_groundwork.delivery.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ironical_groundwork.delivery.model.Login
import com.ironical_groundwork.delivery.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class DeliveryViewModel(private val repository: Repository): ViewModel() {
    val loginPostResponse: MutableLiveData<Response<Login>> = MutableLiveData()

    fun pushPostLogin(code: String, userId: String, phoneName: String) {
        viewModelScope.launch {
            val response = repository.pushPostLogin(code, userId, phoneName)
            loginPostResponse.value = response
        }
    }
}
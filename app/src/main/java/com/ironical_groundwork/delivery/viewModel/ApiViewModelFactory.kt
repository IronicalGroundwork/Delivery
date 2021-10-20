package com.ironical_groundwork.delivery.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ironical_groundwork.delivery.repository.ApiRepository

class ApiViewModelFactory(
    private val ApiRepository: ApiRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ApiViewModel(ApiRepository) as T
    }
}
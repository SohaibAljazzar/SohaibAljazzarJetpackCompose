package com.example.sohaibaljazzarjetpackcompose.app.main_screens.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sohaibaljazzarjetpackcompose.ApiService
import com.example.sohaibaljazzarjetpackcompose.CreateOrderRequestResponse
import kotlinx.coroutines.launch


class CreateOrderViewModel : ViewModel() {
    private val apiService: ApiService = ApiService.geInstance()

    private val _newOrderRequestResponse = MutableLiveData<CreateOrderRequestResponse>()
    val newOrderRequestResponse: LiveData<CreateOrderRequestResponse> = _newOrderRequestResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun createOrder(
        token: String,
        userId: Int,
        workId: Int,
        details: String,
        lat: String,
        long: String,
        phone: String,
    ) {

        viewModelScope.launch {
            try {
                val response = apiService.createOrder(
                    token,
                    userId,
                    workId,
                    details,
                    "detailsAddress",
                    lat,
                    long,
                    phone,
                )
                if (response.isSuccessful) {
                    val newRequestResponse = response.body()
                    _newOrderRequestResponse.value = newRequestResponse
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = getErrorMessageFromResponse(errorBody)
                    _error.value = errorMessage
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    private fun getErrorMessageFromResponse(errorBody: String?): String {
        return errorBody.toString()
    }
}

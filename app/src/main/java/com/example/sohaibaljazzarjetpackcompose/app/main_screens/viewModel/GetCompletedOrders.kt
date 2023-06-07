package com.example.sohaibaljazzarjetpackcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GetCompleteOrders : ViewModel() {

    var listCompleteOrderLiveData: List<OrderRequestData> by mutableStateOf(listOf())


    fun getCompleteOrders(token:String) {
        viewModelScope.launch {
            val apiService = ApiService.geInstance()
            val getComplete = apiService.getCompletedOrder(token)

            listCompleteOrderLiveData = getComplete.data


        }


    }
}
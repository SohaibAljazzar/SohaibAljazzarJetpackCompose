package com.example.sohaibaljazzarjetpackcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GetPendingOrdersViewModel : ViewModel() {

    var listPendingOrdersLiveData: List<OrderRequestData> by mutableStateOf(listOf())


    fun getPendingOrders(token:String) {
        viewModelScope.launch {
            val apiService = ApiService.geInstance()
            val getPending = apiService.getPendingOrders(token)

            listPendingOrdersLiveData = getPending.data


        }
    }

}
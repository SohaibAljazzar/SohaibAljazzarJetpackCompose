package com.example.sohaibaljazzarjetpackcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AllWorkViewModel : ViewModel() {

    var listAllWorkLiveData: List<AllWorkData> by mutableStateOf(listOf())


    fun getAllWork() {
        viewModelScope.launch {
            val apiService = ApiService.geInstance()
            val getAllWorkLis = apiService.getAllWorkResponse()

            listAllWorkLiveData = getAllWorkLis.data


        }
    }

}
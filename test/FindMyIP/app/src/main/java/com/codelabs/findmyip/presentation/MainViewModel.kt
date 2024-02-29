package com.codelabs.findmyip.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.findmyip.domain.repository.Repository
import com.codelabs.findmyip.model.IPModel
import com.codelabs.findmyip.utiles.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    val repository: Repository
    ) : ViewModel() {
    private val _ip:MutableStateFlow<ResponseState<IPModel>> = MutableStateFlow(ResponseState.Loading())
    val ip: StateFlow<ResponseState<IPModel>> = _ip

    init{
        getIP()
    }

     fun getIP() {
        viewModelScope.launch {
            repository.getIPDetails().collectLatest { value->
                _ip.value = value
            }
        }

    }

}
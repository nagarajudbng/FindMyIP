package com.codelabs.findmyip.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.findmyip.client.RepositoryInstance
import com.codelabs.findmyip.model.IpModel
import com.codelabs.findmyip.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _ip: MutableStateFlow<ResourceState<IpModel>> = MutableStateFlow(ResourceState.Loading())
    val ip: StateFlow<ResourceState<IpModel>> = _ip
    init{
        getIP()
    }
    private fun getIP(){
        viewModelScope.launch {
            RepositoryInstance.getMyIPDetails().collectLatest { value ->
                    _ip.value = value
            }
        }
    }
}
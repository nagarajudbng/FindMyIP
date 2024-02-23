package com.codelabs.findmyip.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.findmyip.client.Repository
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

class MainViewModel (private val repository: Repository) : ViewModel() {

    private val _ip: MutableStateFlow<ResourceState<IpModel>> = MutableStateFlow(ResourceState.Loading())
    val ip: StateFlow<ResourceState<IpModel>> = _ip
    init{
        getIP()
    }
    public fun getIP(){
        viewModelScope.launch {
            _ip.value = ResourceState.Loading()
            try {
                repository.getMyIPDetails().collectLatest { value ->
                    _ip.value = value
                }
            } catch(exception:Exception){
                _ip.value = ResourceState.Error("Fail")
            }
        }
    }
}
package com.codelabs.findmyip.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.codelabs.findmyip.client.RepositoryInstance

class MainViewModelFactory(private val repository: RepositoryInstance) :ViewModelProvider.Factory{

    override fun <T:ViewModel> create(modelclass: Class<T>):T{
        return MainViewModel(repository) as T
    }

}
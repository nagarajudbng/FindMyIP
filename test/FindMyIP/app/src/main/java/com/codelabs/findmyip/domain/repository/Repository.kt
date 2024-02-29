package com.codelabs.findmyip.domain.repository

import com.codelabs.findmyip.model.IPModel
import com.codelabs.findmyip.utiles.ResponseState
import kotlinx.coroutines.flow.Flow

interface Repository {
     suspend fun getIPDetails(): Flow<ResponseState<IPModel>>
}
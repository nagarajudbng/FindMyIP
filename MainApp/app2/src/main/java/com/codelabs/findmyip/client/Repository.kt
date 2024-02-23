package com.codelabs.findmyip.client

import com.codelabs.findmyip.model.IpModel
import com.codelabs.findmyip.utilities.ResourceState
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getMyIPDetails(): Flow<ResourceState<IpModel>>
}

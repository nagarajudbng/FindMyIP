package com.codelabs.findmyip.client

import com.codelabs.findmyip.model.IpModel
import com.codelabs.findmyip.services.IpService
import com.codelabs.findmyip.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class RepositoryInstance:Repository {
        suspend fun apiService(): IpService {
            return Retrofit.Builder()
                .baseUrl("https://ipapi.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IpService::class.java)
        }
    override suspend fun getMyIPDetails(): Flow<ResourceState<IpModel>> {
            return flow {
                emit(ResourceState.Loading())

                val response = apiService().getIPDetails()

                if (response.isSuccessful && response.body() != null) {
                    emit(ResourceState.Success(response.body()!!))
                } else {
                    emit(ResourceState.Error("Error fetching news data"))
                }
            }.catch { e ->
                emit(ResourceState.Error(e?.localizedMessage ?: "Some error in flow"))
            }
        }
}
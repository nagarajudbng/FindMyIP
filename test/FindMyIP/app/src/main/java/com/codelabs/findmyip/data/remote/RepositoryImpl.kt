package com.codelabs.findmyip.data.remote

import com.codelabs.findmyip.domain.repository.Repository
import com.codelabs.findmyip.model.IPModel
import com.codelabs.findmyip.service.IPService
import com.codelabs.findmyip.utiles.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RepositoryImpl : Repository {
   suspend fun getService() : IPService{
        return Retrofit.Builder()
            .baseUrl("https://ipapi.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IPService::class.java)
    }
    override suspend fun getIPDetails(): Flow<ResponseState<IPModel>> {
        return flow {
            emit(ResponseState.Loading())
            val response = getService().getIPDetails()
            if(response.isSuccessful && response.body()!=null){
                emit(ResponseState.Success(response.body()!!))
            } else{
                emit(ResponseState.Fail("Request Failed"))
            }
        }.catch { e->
            emit(ResponseState.Fail(e.message+" some error in flow"))
        }
    }
}
package com.codelabs.findmyip.services

import com.codelabs.findmyip.model.IpModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface IpService {
    @GET("/json")
    suspend fun getIPDetails(): Response<IpModel>
}
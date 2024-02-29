package com.codelabs.findmyip.service

import com.codelabs.findmyip.model.IPModel
import retrofit2.Response
import retrofit2.http.GET

interface IPService {
    @GET("/json")
    suspend fun getIPDetails():Response<IPModel>
}
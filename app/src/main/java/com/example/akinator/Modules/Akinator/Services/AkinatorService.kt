package com.example.akinator.Modules.Akinator.Services

import com.example.akinator.Modules.Akinator.Models.SessionModel
import retrofit2.Call
import retrofit2.http.GET

interface AkinatorService {
    @GET("start")
    fun start(): Call<SessionModel>
}
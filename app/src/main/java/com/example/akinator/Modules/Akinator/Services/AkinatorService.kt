package com.example.akinator.Modules.Akinator.Services

import com.example.akinator.Modules.Akinator.Entities.SessionEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AkinatorService {
    @GET("start")
    fun start(): Call<SessionEntity>

    @POST("continue/{session_id}")
    fun continueSession(
        @Path("session_id") sessionId: Int,
    ): Call<SessionEntity>
}
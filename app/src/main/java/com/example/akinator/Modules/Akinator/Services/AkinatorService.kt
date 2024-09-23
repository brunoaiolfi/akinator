package com.example.akinator.Modules.Akinator.Services

import com.example.akinator.Modules.Akinator.Entities.QuestionEntity
import com.example.akinator.Modules.Akinator.Entities.SessionEntity
import retrofit2.Call
import retrofit2.http.Body
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

    data class AnswerRequest(
        val answer: Int,
    )
    @POST("answer/{session_id}")
    fun answer(
        @Path("session_id") sessionId: Int,
        @Body() answer: AnswerRequest,
    ): Call<QuestionEntity>
}
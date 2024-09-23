package com.example.akinator.Modules.Akinator.Entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SessionEntity(
    @SerializedName("session_id")
    var sessionId: Int = 0,

    @SerializedName("question")
    var question: QuestionEntity = QuestionEntity()
) : Serializable
package com.example.akinator.Modules.Akinator.Models

import com.google.gson.annotations.SerializedName

class SessionModel {
    @SerializedName("session_id")
    var sessionId: Int = 0

    @SerializedName("question")
    var question: QuestionModel = QuestionModel()
}
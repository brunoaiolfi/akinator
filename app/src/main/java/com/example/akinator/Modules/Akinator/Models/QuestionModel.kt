package com.example.akinator.Modules.Akinator.Models

import com.google.gson.annotations.SerializedName

class QuestionModel {
    @SerializedName("question")
    var questionText: String = ""

    @SerializedName("done")
    var done: Boolean = false

    @SerializedName("progress")
    var progress: Double = 0.0
}
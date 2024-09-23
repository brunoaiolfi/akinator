package com.example.akinator.Modules.Akinator.Entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class QuestionEntity : Serializable {
    @SerializedName("question")
    var questionText: String = ""

    @SerializedName("done")
    var done: Boolean = false

    @SerializedName("progress")
    var progress: Double = 0.0
}
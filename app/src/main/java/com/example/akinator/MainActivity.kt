package com.example.akinator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.akinator.Modules.Akinator.Models.QuestionModel
import com.example.akinator.Modules.Akinator.Models.SessionModel
import com.example.akinator.Modules.Akinator.Services.AkinatorService
import com.example.akinator.infra.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val remote = RetrofitClient.createService(AkinatorService::class.java);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.startGame()
    }

    private fun startGame() {
        val call: Call<SessionModel> = remote.start()
        call.enqueue(object : Callback<SessionModel> {
            override fun onResponse(call: Call<SessionModel>, response: Response<SessionModel>) {
                val session = response.body()
            }

            override fun onFailure(call: Call<SessionModel>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }
}
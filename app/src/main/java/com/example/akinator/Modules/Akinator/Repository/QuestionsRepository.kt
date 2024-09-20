package com.example.akinator.Modules.Akinator.Repository

import com.example.akinator.Modules.Akinator.Models.SessionModel
import com.example.akinator.Modules.Akinator.Services.AkinatorService
import com.example.akinator.infra.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionsRepository {
    companion object {
        private val remote = RetrofitClient.createService(AkinatorService::class.java);

        fun startGame(cb: (SessionModel) -> Unit, cbError: () -> Unit) {
            val call: Call<SessionModel> = remote.start()

            call.enqueue(object : Callback<SessionModel> {
                override fun onResponse(
                    call: Call<SessionModel>,
                    response: Response<SessionModel>
                ) {
                    val session = response.body();
                    if (!response.isSuccessful || response.body() == null) {
                        cbError();
                    }

                    cb(session!!);
                }

                override fun onFailure(call: Call<SessionModel>, t: Throwable) {
                    // Update LiveData when there's an error
                    cbError();
                }
            })
        }
    }
}
package com.example.akinator.Modules.Akinator.Repository

import com.example.akinator.Modules.Akinator.Entities.SessionEntity
import com.example.akinator.Modules.Akinator.Services.AkinatorService
import com.example.akinator.infra.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionsRepository {
    companion object {
        private val remote = RetrofitClient.createService(AkinatorService::class.java);

        fun startGame(cb: (SessionEntity) -> Unit, cbError: () -> Unit) {
            val call: Call<SessionEntity> = remote.start()

            call.enqueue(object : Callback<SessionEntity> {
                override fun onResponse(
                    call: Call<SessionEntity>,
                    response: Response<SessionEntity>
                ) {
                    val session = response.body();
                    if (!response.isSuccessful || response.body() == null) {
                        cbError();
                    }

                    cb(session!!);
                }

                override fun onFailure(call: Call<SessionEntity>, t: Throwable) {
                    // Update LiveData when there's an error
                    cbError();
                }
            })
        }

        fun getSession(id: Int, cb: (session: SessionEntity) -> Unit, cbError: () -> Unit) {
            val call: Call<SessionEntity> = remote.continueSession(id);

            call.enqueue(object : Callback<SessionEntity> {
                override fun onResponse(
                    call: Call<SessionEntity>,
                    response: Response<SessionEntity>
                ) {
                    val session = response.body();

                    if (!response.isSuccessful || response.body() == null) {
                        cbError();
                    }

                    cb(session!!);
                }

                override fun onFailure(call: Call<SessionEntity>, t: Throwable) {
                    // Update LiveData when there's an error
                    cbError();
                }
            })
        }
    }
}
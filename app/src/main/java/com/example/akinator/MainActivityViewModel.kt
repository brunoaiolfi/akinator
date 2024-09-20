package com.example.akinator

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.akinator.Modules.Akinator.Models.SessionModel
import com.example.akinator.Modules.Akinator.Services.AkinatorService
import com.example.akinator.infra.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.activity.viewModels

class MainActivityViewModel : ViewModel() {
    private val remote = RetrofitClient.createService(AkinatorService::class.java);

    // LiveData to notify the UI about toast messages
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    private val _navigateToSecondActivity = MutableLiveData<Boolean>()
    val navigateToSecondActivity: LiveData<Boolean> get() = _navigateToSecondActivity

    fun navigate() {
        _navigateToSecondActivity.value = true
    }

    fun navigationComplete() {
        _navigateToSecondActivity.value = false
    }

    public fun startGame() {
        val call: Call<SessionModel> = remote.start()
        call.enqueue(object : Callback<SessionModel> {
            override fun onResponse(call: Call<SessionModel>, response: Response<SessionModel>) {
                if (!response.isSuccessful) {
                    // Update LiveData when there's an error
                    _toastMessage.value = "Erro ao iniciar o jogo!"
                    return;
                }

                navigate()
            }

            override fun onFailure(call: Call<SessionModel>, t: Throwable) {
                // Update LiveData when there's an error
                _toastMessage.value = "Erro ao iniciar o jogo!"
            }
        })
    }
}
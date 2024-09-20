package com.example.akinator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.akinator.Modules.Akinator.Models.SessionModel
import com.example.akinator.Modules.Akinator.Repository.QuestionsRepository
import com.example.akinator.Modules.Akinator.Services.AkinatorService
import com.example.akinator.infra.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    private val repository = QuestionsRepository;

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

    public fun handleStartGame() {
        repository.startGame(
            cb = { session ->
                // Update LiveData when there's a successful response
                navigate()
            },
            cbError = {
                // Update LiveData when there's an error
                _toastMessage.value = "Error starting the game"
            }
        )
    }
}
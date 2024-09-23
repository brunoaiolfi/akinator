package com.example.akinator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.akinator.Modules.Akinator.Entities.SessionEntity
import com.example.akinator.Modules.Akinator.Repository.QuestionsRepository

class MainActivityViewModel : ViewModel() {

    private val repository = QuestionsRepository;

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    private val _session = MutableLiveData<SessionEntity>()
    val session: LiveData<SessionEntity> = _session;

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
                _session.value = session
                navigate()
            },
            cbError = {
                // Update LiveData when there's an error
                _toastMessage.value = "Error starting the game"
            }
        )
    }
}
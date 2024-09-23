package com.example.akinator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.akinator.Modules.Akinator.Entities.SessionEntity
import com.example.akinator.Modules.Akinator.Repository.QuestionsRepository
import androidx.lifecycle.ViewModelProvider

class MainActivityViewModelFactory(
    private val application: Application,
    private val mainActivityProps: MainActivityProps
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(application, mainActivityProps) as T
    }
}

class MainActivityViewModel(
    application: Application,
    private val mainActivityProps: MainActivityProps
) :
    AndroidViewModel(application) {

    private val repository = QuestionsRepository;

    fun handleStartGame() {
        repository.startGame(
            cb = { session ->
                // Update LiveData when there's a successful response
                mainActivityProps.startGame(session)
            },
            cbError = {
                // Update LiveData when there's an error
                mainActivityProps.showToast("Error starting the game. ${it}")
            }
        )
    }
}
package com.example.akinator.Modules.Akinator.Views.Questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.akinator.Modules.Akinator.Entities.SessionEntity
import com.example.akinator.Modules.Akinator.Repository.QuestionsRepository

class QuestionsViewModel : ViewModel() {

    private val repository = QuestionsRepository;

    private val _session = MutableLiveData<SessionEntity>();
    val session: LiveData<SessionEntity> = _session;

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    fun setSession(session: SessionEntity) {
        _session.value = session;
    }

    fun handleAnswer(answer: Int) {
        val session_id = _session.value?.sessionId ?: return;

        repository.answer(session_id, answer, {
            // Handle success
            _session.value = _session.value?.copy(
                question = it
            )
        }, {
            // Handle error
            _toastMessage.value = "Ocorreu um erro ao responder a pergunta"
        })

    }
}
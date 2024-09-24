package com.example.akinator.Modules.Akinator.Views.Questions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.akinator.Modules.Akinator.Entities.SessionEntity
import com.example.akinator.Modules.Akinator.Repository.QuestionsRepository

class QuestionsViewModelFactory(
    private val application: Application,
    private val questionActivityProps: QuestionActivityProps
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuestionsViewModel(application, questionActivityProps) as T
    }
}

class QuestionsViewModel(application: Application, private val questionsActivityProps: QuestionActivityProps) : AndroidViewModel(application) {

    private val repository = QuestionsRepository;

    private val _session = MutableLiveData<SessionEntity>();
    val session: LiveData<SessionEntity> = _session;

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
            questionsActivityProps.showToast("Ocorreu um erro ao responder a pergunta")
        })

    }
}
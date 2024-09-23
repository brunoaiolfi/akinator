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

    fun setSession(session: SessionEntity) {
        _session.value = session;
    }
}
package com.example.akinator.Modules.Akinator.Views.Questions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.akinator.Modules.Akinator.Entities.SessionEntity
import com.example.akinator.R

class QuestionsActivity : AppCompatActivity() {

    private lateinit var viewModel: QuestionsViewModel;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(QuestionsViewModel::class.java);
        val tempSession = intent.getSerializableExtra("session") as SessionEntity?;

        tempSession?.let {
            viewModel.setSession(it);
        }

        setContentView(R.layout.activity_questions)
    }
}
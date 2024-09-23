package com.example.akinator.Modules.Akinator.Views.Questions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.akinator.Modules.Akinator.Entities.SessionEntity
import com.example.akinator.databinding.ActivityQuestionsBinding

class QuestionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionsBinding
    private lateinit var viewModel: QuestionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Usando ViewBinding para inflar o layout
        binding = ActivityQuestionsBinding.inflate(layoutInflater)

        // Configurando o ViewModel
        viewModel = ViewModelProvider(this).get(QuestionsViewModel::class.java)

        // Obtendo o objeto session enviado pela Intent
        val tempSession = intent.getSerializableExtra("session") as SessionEntity?

        // Se o session foi passado, set no ViewModel
        tempSession?.let {
            viewModel.setSession(it)
        }

        // Definir a view principal da activity como o root do binding
        setContentView(binding.root)

        // Configurar os observadores
        observers()
    }

    fun observers() {
        viewModel.session.observe(this, Observer { session ->
            session?.let {
                // Atualizar o texto da questão quando o LiveData mudar
                binding.txtQuestion.text = it.question.questionText
            }
        })
    }
}

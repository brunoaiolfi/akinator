package com.example.akinator.Modules.Akinator.Views.Questions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.akinator.Modules.Akinator.Entities.Enums.Answer
import com.example.akinator.Modules.Akinator.Entities.SessionEntity
import com.example.akinator.R
import com.example.akinator.databinding.ActivityQuestionsBinding

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityQuestionsBinding
    private lateinit var viewModel: QuestionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Usando ViewBinding para inflar o layout
        binding = ActivityQuestionsBinding.inflate(layoutInflater)

        // Configurando o ViewModel
        viewModel = ViewModelProvider(this)[QuestionsViewModel::class.java]

        // Obtendo o objeto session enviado pela Intent
        val tempSession = intent.getSerializableExtra("session") as SessionEntity?

        // Se o session foi passado, set no ViewModel
        tempSession?.let {
            viewModel.setSession(it)
        }

        // Definir a view principal da activity como o root do binding
        setContentView(binding.root)

        binding.btnIdk.setOnClickListener(this)
        binding.btnNo.setOnClickListener(this)
        binding.btnYes.setOnClickListener(this)

        // Configurar os observadores
        observers()
    }

    fun observers() {
        viewModel.toastMessage.observe(this, Observer { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.session.observe(this, Observer { session ->
            session?.let {
                // Atualizar o texto da questão quando o LiveData mudar
                binding.txtQuestion.text = it.question.questionText
            }
        })
    }

    override fun onClick(v: View?) {
        if (v?.id == binding.btnYes.id) {
            return viewModel.handleAnswer(Answer.YES.value);
        } else if (v?.id == binding.btnNo.id) {
            return viewModel.handleAnswer(Answer.NO.value);
        } else if (v?.id == binding.btnIdk.id) {
            return viewModel.handleAnswer(Answer.IDK.value);
        };
    }
}

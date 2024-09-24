package com.example.akinator.Modules.Akinator.Views.Questions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.akinator.Modules.Akinator.Entities.Enums.Answer
import com.example.akinator.Modules.Akinator.Entities.SessionEntity
import com.example.akinator.R
import com.example.akinator.databinding.ActivityQuestionsBinding

interface QuestionActivityProps {
    fun showToast(message: String)
}

class QuestionsActivity : AppCompatActivity(), View.OnClickListener, QuestionActivityProps {

    private lateinit var binding: ActivityQuestionsBinding
    private val viewModel: QuestionsViewModel by viewModels {
        QuestionsViewModelFactory(
            application, this
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observers();
        bindings();
        getSession();
    }

    fun observers() {
        viewModel.session.observe(this, Observer { session ->
            session?.let {
                binding.txtQuestion.text = it.question.questionText
                binding.progressBar.setProgress((it.question.progress * 100).toInt(), true);

                if (it.question.done) {
                    binding.btnIdk.visibility = View.INVISIBLE
                    binding.btnNo.visibility = View.INVISIBLE
                    binding.btnYes.visibility = View.INVISIBLE
                }
            }
        })
    }

    fun bindings() {
        if (viewModel.session.value?.question?.done == true) return;

        binding.btnIdk.setOnClickListener(this)
        binding.btnNo.setOnClickListener(this)
        binding.btnYes.setOnClickListener(this)
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

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun getSession() {
        val tempSession = intent.getSerializableExtra("session") as SessionEntity?
        tempSession?.let {
            viewModel.setSession(it)
        }
    }
}

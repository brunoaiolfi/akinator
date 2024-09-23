package com.example.akinator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.akinator.databinding.ActivityMainBinding
import com.example.akinator.Modules.Akinator.Entities.SessionEntity
import com.example.akinator.Modules.Akinator.Views.Questions.QuestionsActivity

interface MainActivityProps {
    fun showToast(message: String)
    fun startGame(session: SessionEntity)
}

class MainActivity : AppCompatActivity(), View.OnClickListener, MainActivityProps {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels {
        MainActivityViewModelFactory(
            application,
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindings();
    }

    fun bindings() {
        binding.btnStart.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_start) {
            return viewModel.handleStartGame();
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun startGame(session: SessionEntity) {
        val intent = Intent(this, QuestionsActivity::class.java)

        intent.putExtra("session", session);

        startActivity(intent)
    }
}
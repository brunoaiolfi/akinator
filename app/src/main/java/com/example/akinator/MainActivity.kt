package com.example.akinator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.akinator.databinding.ActivityMainBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.akinator.Modules.Akinator.Entities.SessionEntity
import com.example.akinator.Modules.Akinator.Views.Questions.QuestionsActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    private var session: SessionEntity = SessionEntity();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.btnStart.setOnClickListener(this);

        observers();
    }

    fun observers() {
        viewModel.toastMessage.observe(this, Observer { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.session.observe(this, Observer { session ->
            session?.let {
                this.session = it;
            }
        })

        viewModel.navigateToSecondActivity.observe(this, Observer { shouldNavigate ->
            if (shouldNavigate == true) {
                val intent = Intent(this, QuestionsActivity::class.java)

                // Pass the session
                intent.putExtra("session", this.session);

                startActivity(intent)
                viewModel.navigationComplete()  // Reset the value after navigating
            }
        })
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_start) {
            return viewModel.handleStartGame();
        }
    }
}
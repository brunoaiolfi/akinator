package com.example.akinator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.akinator.Modules.Akinator.Models.QuestionModel
import com.example.akinator.Modules.Akinator.Models.SessionModel
import com.example.akinator.Modules.Akinator.Services.AkinatorService
import com.example.akinator.databinding.ActivityMainBinding
import com.example.akinator.infra.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.akinator.Modules.Akinator.Views.Questions.QuestionsActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

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

        viewModel.navigateToSecondActivity.observe(this, Observer { shouldNavigate ->
            if (shouldNavigate == true) {
                val intent = Intent(this, QuestionsActivity::class.java)
                startActivity(intent)
                viewModel.navigationComplete()  // Reset the value after navigating
            }
        })
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_start) {
            return viewModel.startGame();
        }
    }
}
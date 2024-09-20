package com.example.akinator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.akinator.Modules.Akinator.Models.QuestionModel
import com.example.akinator.Modules.Akinator.Models.SessionModel
import com.example.akinator.Modules.Akinator.Services.AkinatorService
import com.example.akinator.databinding.ActivityMainBinding
import com.example.akinator.infra.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.btnStart.setOnClickListener(this);

    }

    override fun onClick(v: View?) {
        viewModel.startGame();
    }
}
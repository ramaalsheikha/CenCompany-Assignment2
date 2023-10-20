package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.OrderBuildingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val startButton:Button = binding.startBtn
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        start()

       var action = supportActionBar
        action!!.title = "Welcome"
    }
    fun start(){

       startButton.setOnClickListener {
           startActivity(Intent(this,OrderBuildingActivity::class.java))
       }

    }
}
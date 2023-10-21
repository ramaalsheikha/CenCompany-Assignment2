package com.example.myapplication.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        start()

       var action = supportActionBar
        action!!.title = "Welcome"
    }
    fun start(){

       binding.startBtn.setOnClickListener {
           startActivity(Intent(this, OrderBuildingActivity::class.java))
       }

    }
}
package com.example.myapplication.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class OrderBuildingActivity : AppCompatActivity() {
    private val optionsFragment = CoffeeOptionsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_biulding)
        showCoffeeOptionsFragment()

        var action = supportActionBar
        action!!.title = "Order"
    }
    private fun showCoffeeOptionsFragment(){
        addFragment(R.id.coffee_options_fragment,optionsFragment)
    }

    private fun addFragment(id:Int,fragment:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(id,fragment)
        transaction.commit()
    }
}
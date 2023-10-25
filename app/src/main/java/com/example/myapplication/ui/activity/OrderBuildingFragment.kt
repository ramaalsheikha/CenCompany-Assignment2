package com.example.myapplication.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.FragmentOrderBuildingBinding

class OrderBuildingFragment:Fragment() {
    private lateinit var binding:FragmentOrderBuildingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBuildingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        showCoffeeSize()
        showCheckBoxOptions()

    }

    private fun showCheckBoxOptions() {
        val list = listOf(binding.rbSmall,binding.rbMedium,binding.rbLarg)
   list.forEach {
       it.setOnClickListener {
           binding.checkBox.visibility = View.VISIBLE
       }
   }
    }

    private fun showCoffeeSize() {
        val list = listOf(binding.btnAmericano,binding.btnCappuccino,binding.btnMacchiato,binding.btnLatte)
        list.forEach {
            it.setOnClickListener {
                binding.clCoffeeSizePart.visibility = View.VISIBLE
            }
        }
    }

}
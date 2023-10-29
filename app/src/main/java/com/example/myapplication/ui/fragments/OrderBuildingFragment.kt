package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentOrderBuildingBinding

class OrderBuildingFragment : Fragment() {
    private lateinit var binding: FragmentOrderBuildingBinding
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
        initListener()
    }

    private fun initListener() {
        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.paymentFragment)
        }
    }

    private fun showCheckBoxOptions() {
        val list = listOf(binding.rbSmall, binding.rbMedium, binding.rbLarg)
        list.forEach {
            it.setOnClickListener {
                binding.checkBox.visibility = View.VISIBLE
            }
        }
    }

    private fun showCoffeeSize() {
        val list = listOf(
            binding.rbAmericano,
            binding.rbCappuccino,
            binding.rbLatte,
            binding.rbMacchiato
        )
        list.forEach {
            it.setOnClickListener {
                binding.clCoffeeSizePart.visibility = View.VISIBLE
            }
        }
    }
}
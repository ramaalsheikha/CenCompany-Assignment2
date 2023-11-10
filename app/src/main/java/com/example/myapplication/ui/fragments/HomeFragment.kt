package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.constants.Id
import com.example.myapplication.constants.Key
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListener()
    }

    private fun initListener() {

        binding.btnStart.setOnClickListener {
            navigateToOrderBuildingFragment()
        }
    }

    private fun navigateToOrderBuildingFragment() {
        try {
            findNavController().navigate(Id.ORDER_BUILDING_FRAGMENT_ID)
        } catch (error: Exception) {
            logError("Navigation error: ${error.message}")
            showErrorMessageToUser()
        }
    }

    private fun showErrorMessageToUser() {
        Toast.makeText(requireContext(), "Navigation failed. Please try again.", Toast.LENGTH_SHORT)
            .show()
    }

    private fun logError(message: String) {
        Log.e(Key.HOME_FRAGMENT, message)
    }
}
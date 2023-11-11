package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.constants.ErrorMessage
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
            findNavController().navigate(R.id.orderBuildingFragment)
        }
    }

    private fun navigateToOrderBuildingFragment() {
        try {
            findNavController().navigate(Id.ORDER_BUILDING_FRAGMENT_ID)
        } catch (error: Exception) {
            Log.e(Key.HOME_FRAGMENT,error.message.toString())
                ErrorMessage.showErrorMessage(requireContext(),R.string.navigation_failed_please_try_again)
        }
    }


}
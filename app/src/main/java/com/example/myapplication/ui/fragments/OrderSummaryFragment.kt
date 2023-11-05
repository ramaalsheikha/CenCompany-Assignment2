package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentOrderSummaryBinding
import com.example.myapplication.domine.OrderInfo
import com.example.myapplication.domine.PickerTime
import com.example.myapplication.domine.UserInfo
import java.lang.StringBuilder

class OrderSummaryFragment : Fragment() {
    private lateinit var binding: FragmentOrderSummaryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderSummaryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showOrderInfo()
    }

    private fun showOrderInfo() {
        val orderInfo = arguments?.getParcelable<OrderInfo>(getString(R.string.orderinfo))
        val order = orderInfo?.order?.joinToString(separator = " ")
        binding.tvOrderInput.text = order
    }


}
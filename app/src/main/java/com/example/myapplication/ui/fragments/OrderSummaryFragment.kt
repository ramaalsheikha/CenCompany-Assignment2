package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentOrderSummaryBinding
import com.example.myapplication.domine.OrderInfo
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
        // showOrderInfo()
        val userInfo = arguments?.getParcelable<UserInfo>("userInfo")
        binding.tvFullNameInput.text = userInfo?.userName
        binding.tvPhoneNumberInput.text = userInfo?.userPhone
        val orderInfo = arguments?.getParcelable<OrderInfo>("orderInfo")
        binding.tvOrderInput.text = "A ${orderInfo?.coffeeSize} ${orderInfo?.coffeeType} ,with "
    }

//    private fun showOrderInfo() {
//        val orderInfo: OrderInfo? = arguments?.getParcelable<OrderInfo>("orderInfo")
//        val orderText = StringBuilder()
//        if (orderInfo != null) {
//            orderText.append("A ${orderInfo.coffeeSize} ${orderInfo.coffeeType} ,with ")
//            when (orderInfo.checkBox.size) {
//                1 -> orderText.append(orderInfo.checkBox[0])
//                else -> {
//                    for (i in 0 until orderInfo.checkBox.size - 1) {
//                        orderText.append(orderInfo.checkBox[i]).append(", ")
//                    }
//                    orderText.append("and ").append(orderInfo.checkBox.last())
//                }
//            }
//            binding.tvOrderInput.text = orderText
//        }
//    }
}
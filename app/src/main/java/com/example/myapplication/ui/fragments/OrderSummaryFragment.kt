package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.RecyclerViewAdapter
import com.example.myapplication.databinding.FragmentOrderSummaryBinding
import com.example.myapplication.domine.ItemsViewModel
import com.example.myapplication.domine.OrderInfo
import com.example.myapplication.domine.PaymentInfo

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
        showPaymentInfo()
    }

    private fun showPaymentInfo() {
        binding .recyclerView.layoutManager = LinearLayoutManager(context)
        val titleList = listOf("Full Name","Phone Number","Pickup Time","Order")
        val data :MutableList<ItemsViewModel> = mutableListOf()
        val paymentInfo = arguments?.getParcelable<PaymentInfo>(getString(R.string.paymentinfo))
        for (i in 0..3){
            if (i<3) {
                var title = titleList[i]
                var content: String = paymentInfo!!.paymentLisl[i]
                data.add(ItemsViewModel(title, content))
            }
            else {
                val orderInfo = arguments?.getParcelable<OrderInfo>(getString(R.string.orderinfo))
                val order = orderInfo?.order?.joinToString(separator = " ")
                data.add(ItemsViewModel(titleList[i], order.toString()))
            }
        }
        val adapter = RecyclerViewAdapter(data)
        binding.recyclerView.adapter = adapter
    }


}
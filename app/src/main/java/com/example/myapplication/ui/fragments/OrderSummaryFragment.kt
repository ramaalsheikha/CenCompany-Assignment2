package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private val titleList = listOf("Full Name", "Phone Number", "Pickup Time", "Order")
    private val data: MutableList<ItemsViewModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderSummaryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showInfo()
    }

    private fun showInfo() {
        try {
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            val paymentInfo = arguments?.getParcelable<PaymentInfo>(getString(R.string.paymentinfo))
            val orderInfo = arguments?.getParcelable<OrderInfo>(getString(R.string.orderinfo))
            for (i in titleList.indices) {
                val title = titleList[i]
                if (i < titleList.size - 1) {
                    val content: String = paymentInfo!!.paymentLisl[i]
                    data.add(ItemsViewModel(title, content))
                } else {
                    val order = orderInfo?.order?.joinToString(separator = " ")
                    data.add(ItemsViewModel(title, order.toString()))
                }
            }
            val adapter = RecyclerViewAdapter(data)
            binding.recyclerView.adapter = adapter
        } catch (error: Exception) {
            Log.e(SUMMARY_FRAGMENT, "Error: ${error.message}")
            showErrorMessage()
        }
    }

    private fun showErrorMessage() {
        Toast.makeText(
            requireContext(),
            getString(R.string.an_error_occurred_please_try_again), Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        private const val SUMMARY_FRAGMENT = "SummaryFragment"
    }
}
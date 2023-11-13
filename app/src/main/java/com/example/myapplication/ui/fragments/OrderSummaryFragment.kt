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
import com.example.myapplication.constants.ErrorMessage
import com.example.myapplication.constants.Key
import com.example.myapplication.constants.Subtitles
import com.example.myapplication.databinding.FragmentOrderSummaryBinding
import com.example.myapplication.domine.ItemsViewModel
import com.example.myapplication.domine.OrderInfo
import com.example.myapplication.domine.PaymentInfo

class OrderSummaryFragment : Fragment() {
    private lateinit var binding: FragmentOrderSummaryBinding
    private lateinit var paymentInfo: PaymentInfo
    private lateinit var orderInfo: OrderInfo
    private val titleList = listOf(Subtitles.FULL_NAME, Subtitles.PHONE_NUMBER, Subtitles.PICKUP_TIME, Subtitles.ORDER)
    private val data: MutableList<ItemsViewModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderSummaryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getData()
        showInfo()
    }

    private fun getData() {
        paymentInfo = arguments?.getParcelable(getString(R.string.paymentinfo))!!
        orderInfo = arguments?.getParcelable(getString(R.string.orderinfo))!!
    }

    private fun showInfo() {
        try {
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            for (i in titleList.indices) {
                val title = titleList[i]
                if (i < titleList.size - 1) {
                    val content: String = paymentInfo.paymentLisl[i]
                    data.add(ItemsViewModel(title, content))
                } else {
                    val order = orderInfo.order.joinToString(separator = " ")
                    data.add(ItemsViewModel(title, order))
                }
            }
            val adapter = RecyclerViewAdapter(data)
            binding.recyclerView.adapter = adapter
        } catch (e: Exception) {
            ErrorMessage.logMessage(Key.SUMMARY_FRAGMENT, e.message.toString())
            ErrorMessage.showErrorMessage(
                requireContext(),
                R.string.navigation_failed_please_try_again
            )
        }
    }
}
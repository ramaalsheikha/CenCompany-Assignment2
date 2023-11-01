package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPaymentBinding
import com.example.myapplication.domine.OrderInfo
import com.example.myapplication.domine.UserInfo
import java.util.Calendar

class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding
    private var hour:Int = 1
    private var minutes = 0
    private var amPm = ""
    private lateinit var selectedTime: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showSpinnerAndPickupTime()
        showCard()
        initListener()
    }

    private fun initListener() {
        binding.btnPlaceOrder.setOnClickListener {
            //showPickerTime()

            val orderInfo = arguments?.getParcelable<OrderInfo>("orderInfo")
            val userInfo = UserInfo(
                binding.etFullName.text.toString(),
                binding.etPhoneNumber.text.toString(),
            )
            val bundle = Bundle().apply {
                putParcelable("orderInfo", orderInfo)
                putParcelable("userInfo", userInfo)
            }
            findNavController().navigate(R.id.orderSummaryFragment, bundle)
        }
    }

    private fun showPickerTime() {
        binding.clPickerTime.visibility = View.VISIBLE

        binding.npHour.minValue = 1
        binding.npHour.maxValue = 24

        binding.npMinutes.minValue = 0
        binding.npMinutes.maxValue = 59

        val str = arrayOf("AM","PM")
        binding.npAmPm.minValue = 0
        binding.npAmPm.maxValue = str.size
        binding.npAmPm.displayedValues = str

        binding.npHour.setOnValueChangedListener { picker, oldVal, newVal ->
            hour = picker.value
        }
        binding.npMinutes.setOnValueChangedListener { picker, oldVal, newVal ->
            minutes = picker.value
        }
        binding.npAmPm.setOnValueChangedListener { picker, oldVal, newVal ->
            val i = picker.value
            amPm = str[i]
        }

    }

    private fun showSpinnerAndPickupTime() {
        binding.btnContinou.setOnClickListener {
            if (isValidate()) {
                binding.clSpinner.visibility = View.VISIBLE
                showPickerTime()
               // binding.clPickerTime.visibility = View.VISIBLE
                binding.btnContinou.visibility = View.GONE

            }

        }
    }

    private fun isValidate(): Boolean {
        var isVal = false
        if (binding.etFullName.length() <= 0) {
            binding.etFullName.error = "please enter your name"
            isVal = false
        } else {
            isVal = true
        }
        if (binding.etPhoneNumber.length() != 10) {
            binding.etPhoneNumber.error = "failed phone number"
            isVal = false
        } else {
            isVal = true
        }
        return isVal
    }

    private fun showCard() {
        ArrayAdapter.createFromResource(
            binding.spCardType.context,
            R.array.cardType,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spCardType.adapter = adapter
        }
        binding.spCardType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()

                if (selectedItem.isEmpty()) {
                    binding.clCard.visibility = View.GONE
                } else {
                    binding.clCard.visibility = View.VISIBLE
                    binding.btnPlaceOrder.visibility = View.VISIBLE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.clCard.visibility = View.GONE
            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }
        }
    }
}
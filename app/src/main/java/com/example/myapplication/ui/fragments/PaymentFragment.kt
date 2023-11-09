package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPaymentBinding
import com.example.myapplication.domine.OrderInfo
import com.example.myapplication.domine.PaymentInfo
import java.util.regex.Pattern

class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding
    private lateinit var name: String
    private lateinit var phoneNumber: String
    private var hour: Int = 1
    private var minutes = 0
    private var amPm = ""
    private var pickupTime: String = ""
    private var paymentList: MutableList<String> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
        setupListener()
    }

    private fun setupUI() {
        showSpinnerAndPickupTime()
        setSpinnerAdapter()
        showCard()
    }

    private fun setupListener() {
        binding.btnPlaceOrder.setOnClickListener {
            try {
                paymentListBuilding()
                val orderInfo = arguments?.getParcelable<OrderInfo>(getString(R.string.orderinfo))
                val paymentInfo = PaymentInfo(paymentList)

                val bundle = Bundle().apply {
                    putParcelable(getString(R.string.orderinfo), orderInfo)
                    putParcelable(getString(R.string.paymentinfo), paymentInfo)
                }
                if (isValidateCard()) {
                    findNavController().navigate(ORDER_SUMMARY_FRAGMENT_ID, bundle)
                }
            }catch (error:Exception){
                Log.e(PAYMENT_FRAGMENT, "Error: ${error.message}")
                showErrorMessageToUser()
            }
        }
    }

    private fun showErrorMessageToUser() {
        Toast.makeText(requireContext(), "An error occurred. Please try again.", Toast.LENGTH_SHORT).show()
    }

    private fun paymentListBuilding() {
        name = binding.etFullName.text.toString()
        phoneNumber = binding.etPhoneNumber.text.toString()
        pickupTime = "$hour:$minutes $amPm"
        paymentList.apply {
            add(name)
            add(phoneNumber)
            add(pickupTime)
        }
    }

    private fun isValidateCard(): Boolean {

        val date = binding.etCardDate.text
        val regex = getString(R.string.card_date_regex).toRegex()
        val pattern: Pattern = Pattern.compile(regex.toString())
        val matcher = pattern.matcher(date)
        val isCorrectDate: Boolean = matcher.matches()

        var isVal = true
        if (binding.etCardNumber.length() != 16) {
            binding.etCardNumber.error = getString(R.string.error_valid_card_number)
            isVal = false
        } else {
            binding.etCardNumber.error = null
        }
        if (!isCorrectDate) {
            binding.etCardDate.error = getString(R.string.error_valid_card_date)
            isVal = false
        } else {
            binding.etCardDate.error = null
        }
        if (binding.etCardCvv.length() != 3) {
            binding.etCardCvv.error = getString(R.string.error_valid_card_cvv)
            isVal = false
        } else {
            binding.etCardCvv.error = null
        }
        return isVal
    }

    private fun showPickerTime() {
        val visibility = View.VISIBLE
        binding.clPickerTime.visibility = visibility

        val minValueHour = 1
        val maxValueHour = 24
        binding.npHour.minValue = minValueHour
        binding.npHour.maxValue = maxValueHour

        val minValueMinutes = 0
        val maxValueMinutes = 59
        binding.npMinutes.minValue = minValueMinutes
        binding.npMinutes.maxValue = maxValueMinutes

        val str = arrayOf(
            getString(R.string.first_element),
            getString(R.string.am),
            getString(R.string.pm)
        )
        val minValueAmPm = 0
        val maxValueAmPm = str.size - 1
        binding.npAmPm.minValue = minValueAmPm
        binding.npAmPm.maxValue = maxValueAmPm
        binding.npAmPm.displayedValues = str

        binding.npHour.setOnValueChangedListener { picker, _, _ ->
            hour = picker.value

        }
        binding.npMinutes.setOnValueChangedListener { picker, _, _ ->
            minutes = picker.value

        }
        binding.npAmPm.setOnValueChangedListener { picker, _, _ ->
            val i = picker.value
            amPm = str[i]
        }

    }

    private fun showSpinnerAndPickupTime() {
        binding.btnContinou.setOnClickListener {
            if (isValidateNameAndNumber()) {
                binding.clSpinner.visibility = View.VISIBLE
                showPickerTime()
                binding.btnContinou.visibility = View.GONE

            }
        }
    }

    private fun isValidateNameAndNumber(): Boolean {
        val isVal: Boolean
        if (binding.etFullName.length() == 0) {
            binding.etFullName.error = getString(R.string.error_valid_name)
        } else {
            binding.etFullName.error = null
        }
        if (binding.etPhoneNumber.length() != 10) {
            binding.etPhoneNumber.error = getString(R.string.error_valid_number)
            isVal = false
        } else {
            binding.etPhoneNumber.error = null
            isVal = true
        }
        return isVal
    }

    private fun setSpinnerAdapter(){
        ArrayAdapter.createFromResource(
            binding.spCardType.context,
            R.array.cardType,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spCardType.adapter = adapter
        }
    }

    private fun showCard() {

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
    companion object{
        private const val PAYMENT_FRAGMENT = "PaymentFragment"
        private  val ORDER_SUMMARY_FRAGMENT_ID= R.id.orderSummaryFragment
    }
}
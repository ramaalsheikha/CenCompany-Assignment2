package com.example.myapplication.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPaymentBinding

class PaymentFragment:Fragment() {
    private lateinit var binding:FragmentPaymentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListener()
        showSpinner()
    }
    private fun initListener() {
        binding.btnContinou.setOnClickListener {
if (isValidate()) {
    binding.clSpinner.visibility = View.VISIBLE
    binding.clPickerTime.visibility = View.VISIBLE
    binding.btnContinou.visibility = View.GONE
          }
       }
    }
    private fun isValidate(): Boolean {
        var isVal =false
        if (binding.etFullName.length() <= 0){
            binding.etFullName.error = "please enter your name"
            isVal =false
        }else{
            isVal =true
        }
       if (binding.etPhoneNumber.length() != 10 ) {
           binding.etPhoneNumber.error = "failed phone number"
           isVal =false
       }else{
           isVal =true
       }
        return isVal
    }
    private fun showSpinner() {
        ArrayAdapter.createFromResource(
            binding.spCardType.context,
            R.array.cardType,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
           binding.spCardType.adapter = adapter
        }
        binding.spCardType.onItemSelectedListener = object :AdapterView.OnItemSelectedListener,
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
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.clCard.visibility= View.GONE
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
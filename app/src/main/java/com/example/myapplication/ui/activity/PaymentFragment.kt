package com.example.myapplication.ui.activity


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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
        showSpinner()
        showCardPart()


    }

    private fun showCardPart() {
        binding.spCardType.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCardType = binding.spCardType.selectedItem.toString()
                binding.clCard.visibility = View.VISIBLE
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
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
        binding.etPhoneNumber.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (binding.etFullName.text.isNotEmpty()) {
                    binding.clSpinner.visibility = View.VISIBLE
                }
                else{
                    Toast.makeText(context,"Enter you Full Name",Toast.LENGTH_SHORT).show()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }
}
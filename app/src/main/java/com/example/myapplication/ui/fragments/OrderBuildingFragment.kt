package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentOrderBuildingBinding
import com.example.myapplication.domine.OrderInfo

class OrderBuildingFragment : Fragment() {
    private lateinit var selectedCoffeeType: String
    private lateinit var selectedCoffeeSize: String
    private var orderList: MutableList<String> = mutableListOf()
    private var checkBoxList: MutableList<String> = mutableListOf()
    private lateinit var binding: FragmentOrderBuildingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBuildingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        showCoffeeSize()
        showCheckBoxOptions()
        initListener()
    }

    private fun selectedCheckBox() {
        val list = listOf(
            binding.cbSugar,
            binding.cbCream,
            binding.cb2shots,
            binding.cb2milk,
            binding.cbAlmondMilk,
            binding.cbWholeMilk
        )
        list.forEach {
            if (it.isChecked) {
                checkBoxList.add(it.text.toString())

            } else {

            }
        }
    }

    private fun initListener() {
        binding.btnContinue.setOnClickListener {
            try {
                selectedCoffeeTypeAndSize()
                selectedCheckBox()
                orderBuilding()
                val orderInfo = OrderInfo(orderList)
                val bundle = Bundle().apply {
                    putParcelable(getString(R.string.orderinfo), orderInfo)
                }
                findNavController().navigate(PAYMENT_FRAGMENT_ID, bundle)
            }catch (e: Exception) {
                Log.e("OrderBuildingFragment", "Navigation error: ${e.message}")
                showErrorMessageToUser()
            }
        }
    }

    private fun showErrorMessageToUser() {
        Toast.makeText(requireContext(), "Navigation failed. Please try again.", Toast.LENGTH_SHORT).show()
    }

    private fun selectedCoffeeTypeAndSize() {
        val list = listOf(
            binding.rbAmericano,
            binding.rbCappuccino,
            binding.rbLatte,
            binding.rbMacchiato
        )
        list.forEach {
            if (it.isChecked){
                selectedCoffeeType = it.text.toString()
            }
        }
        val listTwo = listOf(binding.rbSmall, binding.rbMedium, binding.rbLarg)
        listTwo.forEach {
            if (it.isChecked){
                selectedCoffeeSize = it.text.toString()
            }
        }

    }

    private fun orderBuilding() {
        orderList.add("A $selectedCoffeeSize $selectedCoffeeType, with ")
        if (checkBoxList.size==1){
            orderList.add(checkBoxList[0])
        }else {
            for (i in 0 until checkBoxList.size - 1) {
                orderList.add(checkBoxList[i] + ",")
            }
            orderList.add("and " + checkBoxList.last())
        }
    }

    private fun showCheckBoxOptions() {
        val list = listOf(binding.rbSmall, binding.rbMedium, binding.rbLarg)
        list.forEach {
            it.setOnClickListener {
                binding.checkBox.visibility = View.VISIBLE
                binding.btnContinueTwo.visibility = View.GONE
                binding.btnContinue.visibility = View.VISIBLE
            }

        }
    }

    private fun showCoffeeSize() {
        val list = listOf(
            binding.rbAmericano,
            binding.rbCappuccino,
            binding.rbLatte,
            binding.rbMacchiato
        )
        list.forEach {
            it.setOnClickListener {
                binding.clCoffeeSizePart.visibility = View.VISIBLE
                binding.btnContinueOne.visibility = View.GONE
                binding.btnContinueTwo.visibility = View.VISIBLE
            }


        }
    }
    companion object {
        private  val PAYMENT_FRAGMENT_ID = R.id.paymentFragment
    }

}
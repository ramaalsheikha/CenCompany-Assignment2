package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.constants.ErrorMessage
import com.example.myapplication.constants.Key
import com.example.myapplication.databinding.FragmentOrderBuildingBinding
import com.example.myapplication.domine.OrderInfo

class OrderBuildingFragment : Fragment() {
    private lateinit var binding: FragmentOrderBuildingBinding
    private lateinit var selectedCoffeeType: String
    private lateinit var selectedCoffeeSize: String
    private var orderList: MutableList<String> = mutableListOf()
    private var checkBoxList: MutableList<String> = mutableListOf()
    private val coffeeTypeList by lazy {
        listOf(
            binding.rbAmericano, binding.rbCappuccino, binding.rbLatte, binding.rbMacchiato
        )
    }
    private val coffeeSizeList by lazy {
        listOf(binding.rbSmall, binding.rbMedium, binding.rbLarg)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBuildingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupIU()
        setupListener()
    }

    private fun setupIU() {
        showCoffeeSize()
        showCheckBoxOptions()
    }

    private fun setupListener() {
        binding.btnContinue.setOnClickListener {
            try {
                handleOrder()
            } catch (e: Exception) {
                ErrorMessage.logMessage(Key.ORDER_BUILDING__FRAGMENT, e.message.toString())
                ErrorMessage.showErrorMessage(
                    requireContext(),
                    R.string.navigation_failed_please_try_again
                )
            }
        }
    }

    private fun handleOrder() {
        selectedCoffeeType()
        selectedCoffeeSize()
        selectedCheckBox()
        orderBuilding()
        val orderInfo = OrderInfo(orderList)
        val bundle = Bundle().apply {
            putParcelable(getString(R.string.orderinfo), orderInfo)
        }
        findNavController().navigate(R.id.paymentFragment, bundle)
    }

    private fun showCoffeeSize() {
        coffeeTypeList.forEach {
            it.setOnClickListener {
                binding.clCoffeeSizePart.visibility = View.VISIBLE
                binding.btnContinueOne.visibility = View.GONE
            }
        }
    }

    private fun showCheckBoxOptions() {
        coffeeSizeList.forEach {
            it.setOnClickListener {
                binding.checkBox.visibility = View.VISIBLE
                binding.btnContinueTwo.visibility = View.GONE
                binding.btnContinue.visibility = View.VISIBLE
            }
        }
    }

    private fun selectedCheckBox() {
        val coffeeCheckBoxList = listOf(
            binding.cbSugar,
            binding.cbCream,
            binding.cb2shots,
            binding.cb2milk,
            binding.cbAlmondMilk,
            binding.cbWholeMilk
        )
        checkBoxList.addAll(
            coffeeCheckBoxList
                .filter { it.isChecked }
                .map { it.text.toString() }
        )
    }

    private fun selectedCoffeeSize() {
        selectedCoffeeSize = selectedValue(coffeeSizeList).joinToString()
    }

    private fun selectedCoffeeType() {
        selectedCoffeeType = selectedValue(coffeeTypeList).joinToString()
    }

    private fun selectedValue(list: List<RadioButton>): List<String> {
        return list.filter { it.isChecked }.map { it.text.toString() }
    }

    private fun orderBuilding() {
        orderList.add(getString(R.string.a_with, selectedCoffeeSize, selectedCoffeeType))
        if (checkBoxList.size == 1) {
            orderList.add(checkBoxList[0])
        } else {
            for (i in 0 until checkBoxList.size - 1) {
                orderList.add(checkBoxList[i] + getString(R.string.comma))
            }
            orderList.add(getString(R.string.and) + checkBoxList.last())
        }
    }
}
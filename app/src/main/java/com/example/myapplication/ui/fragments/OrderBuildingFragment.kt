package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentOrderBuildingBinding
import com.example.myapplication.domine.OrderInfo

class OrderBuildingFragment : Fragment() {
    private var selectedCoffeeType: String = ""
    private var selectedCoffeeSize: String = ""
    private var checkBoxList = mutableListOf<String>()
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
        selectedCheckBox()
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
            if (it.isChecked()) {
                checkBoxList.add(it.text.toString())
            }

        }
    }

    private fun initListener() {
        binding.btnContinue.setOnClickListener {
            val orderInfo = OrderInfo(selectedCoffeeType,selectedCoffeeSize,checkBoxList)
            val bundle = bundleOf().apply {
                putParcelable("orderInfo",orderInfo)
            }
            findNavController().navigate(R.id.paymentFragment,bundle)
        }
    }

    private fun showCheckBoxOptions() {
        val list = listOf(binding.rbSmall, binding.rbMedium, binding.rbLarg)
        list.forEach {
            it.setOnClickListener {
                binding.checkBox.visibility = View.VISIBLE
            }
            selectedCoffeeSize = it.text.toString()

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
            }
            selectedCoffeeType = it.text.toString()
        }
    }

}
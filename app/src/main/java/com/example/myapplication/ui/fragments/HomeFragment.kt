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
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListener()
    }

    private fun initListener() {

        binding.btnStart.setOnClickListener {
            try {
                findNavController().navigate(ORDER_BUILDING_FRAGMENT_ID)
            } catch (e:Exception){
                Log.e(HOME_FRAGMENT, "Navigation error: ${e.message}")
                showErrorMessageToUser()
            }
        }
    }

    private fun showErrorMessageToUser() {
        Toast.makeText(requireContext(), "Navigation failed. Please try again.", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private  val ORDER_BUILDING_FRAGMENT_ID = R.id.orderBuildingFragment
        private const val HOME_FRAGMENT = "HomeFragment"
    }
}
package com.example.workshops.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.workshops.adapters.AdapterWorkshop
import com.example.workshops.R
import com.example.workshops.utils.Utils
import com.example.workshops.databinding.FragmentWorkshopBinding
import com.example.workshops.roomDb.Workshops
import com.example.workshops.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class WorkshopFragment : Fragment() {
    private lateinit var binding : FragmentWorkshopBinding
     private val mainViewModel : MainViewModel by viewModels()
    private lateinit var adapterWorkshop: AdapterWorkshop
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkshopBinding.inflate(layoutInflater)
        setStatusBarColor()
        // below code is used to store the data initially

//        binding.btn.setOnClickListener {
//        val name = binding.name.text.toString()
//        val date = binding.date.text.toString()
//            mainViewModel.ss(Workshops(workshopName = name , workShopDate = date , applied = 0))
//        }
        lifecycleScope.launch {  gettingAllWorkShops()}
        return binding.root
    }

    private   fun gettingAllWorkShops() {
        mainViewModel.getAllWorkshop().observe(viewLifecycleOwner) {workshopList->
            adapterWorkshop = AdapterWorkshop(requireContext(),::onApplyButtonClicked)
            binding.rvWorkshops.adapter = adapterWorkshop
            adapterWorkshop.differ.submitList(workshopList)
        }
    }

    private fun setStatusBarColor() {
        activity?.window?.apply {
            val statusBarColors = ContextCompat.getColor(requireContext(), R.color.blue)
            statusBarColor = statusBarColors
        }
    }

    private fun onApplyButtonClicked(workshops: Workshops){
        val currentUser = FirebaseAuth.getInstance().currentUser
        // condition for button to show
        if (currentUser == null){
            findNavController().navigate(R.id.action_workshopFragment_to_signInFragment)
            Utils.showToast(requireContext() , "Please signInFirst")
        }
        else if(workshops.applied == 1){
            Utils.showToast(requireContext(),"Already applied for it")
        }
        else{
            workshops.applied = 1
            mainViewModel.updateWorkShop(workshops)
        }
    }
}
package com.example.workshops.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.workshops.adapters.AdapterWorkshop
import com.example.workshops.R
import com.example.workshops.databinding.FragmentStudentDashboardBinding
import com.example.workshops.roomDb.Workshops
import com.example.workshops.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth

class StudentDashboardFragment : Fragment() {
    private lateinit var binding : FragmentStudentDashboardBinding
    val mainViewModel : MainViewModel by viewModels()
    private lateinit var adapterWorkshop: AdapterWorkshop
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDashboardBinding.inflate(layoutInflater)

        setStatusBarColor()

        //retrieving the applied workshop
        getAppliedWorkshops()

        // going to Workshop Screen
        onExploreButtonClicked()

        //Logging out the user
        onLogOut()

        return binding.root
    }

    private fun onLogOut() {
        binding.tbWorkshops.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.logOut ->{
                    showLogOutDialog()
                    true
                }

                else -> {false}
            }
        }
    }

    private fun showLogOutDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val alertDialog = builder.create()
        builder.setTitle("Log Out")
            .setMessage("Are you sure you want to logout")
            .setPositiveButton("Yes") { _, _ ->
                FirebaseAuth.getInstance().signOut()
                mainViewModel.settingLoginStatus(false)
                findNavController().navigate(R.id.action_studentDashboardFragment_to_signInFragment)
            }
            .setNegativeButton("NO") { _, _ ->
                alertDialog.dismiss()
            }
            .show()
            .setCancelable(false)
    }

    private fun onExploreButtonClicked() {
        binding.btnExplore.setOnClickListener {
            findNavController().navigate(R.id.action_studentDashboardFragment_to_workshopFragment)
        }
    }

    private fun getAppliedWorkshops() {
        mainViewModel.getAppliedWorkshops().observe(viewLifecycleOwner) {workshopList->
            Log.d("ll",workshopList.toString())
            if(workshopList.isEmpty()){
                binding.tvText.visibility = View.VISIBLE
            }
            else{
                binding.tvText.visibility = View.GONE
            }
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

}

fun onApplyButtonClicked(workshops: Workshops) {
    // nothing to do here
}

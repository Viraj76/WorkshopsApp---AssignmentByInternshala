package com.example.workshops.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.workshops.R
import com.example.workshops.viewmodel.MainViewModel

class SplashFragment : Fragment() {
    private val viewModel : MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setStatusBarColor()

        // splash for 2 second
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.gettingLoginStatus().observe(viewLifecycleOwner){status->
                //check for current user
                if(!status){
                    findNavController().navigate(R.id.action_splashFragment_to_workshopFragment)
                }
                else{
                    findNavController().navigate(R.id.action_splashFragment_to_studentDashboardFragment2)
                }
            }
        },2000)

        return inflater.inflate(R.layout.fragment_splash, container, false)

    }

    private fun setStatusBarColor() {
        activity?.window?.apply {
            val statusBarColors = ContextCompat.getColor(requireContext(), R.color.blue)
            statusBarColor = statusBarColors
        }
    }
}
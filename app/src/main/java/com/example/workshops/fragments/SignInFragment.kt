package com.example.workshops.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.workshops.R
import com.example.workshops.utils.Utils
import com.example.workshops.databinding.FragmentSignInBinding
import com.example.workshops.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth


class SignInFragment : Fragment() {
    private lateinit var binding : FragmentSignInBinding
    private val mainViewModel : MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentSignInBinding.inflate(layoutInflater)
        //going to sign up screen
        onSignUpClick()
        // logging  in the user
        onLoginButtonClick()
        return binding.root
    }

    private fun onLoginButtonClick() {
        binding.btnLogin.setOnClickListener {
            Utils.showDialog(requireContext(),"Signing you...")
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    Utils.hideDialog()
                    findNavController().navigate(R.id.action_signInFragment_to_studentDashboardFragment)
                    mainViewModel.settingLoginStatus(true)
                }
        }
    }

    private fun onSignUpClick() {
        binding.tvSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_signInFragment_to_signOutFragment)
        }
    }


}
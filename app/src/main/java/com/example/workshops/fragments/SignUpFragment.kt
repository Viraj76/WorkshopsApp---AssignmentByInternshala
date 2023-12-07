package com.example.workshops.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.workshops.R
import com.example.workshops.utils.Utils
import com.example.workshops.databinding.FragmentSignOutBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {

    private lateinit var binding : FragmentSignOutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignOutBinding.inflate(layoutInflater)

        // registering the new user
        onRegisterButtonClick()

        // going to sign in fragment
        onSignUpClick()

        return binding.root
    }

    private fun onSignUpClick() {
        binding.tvSignIn.setOnClickListener{
            findNavController().navigate(R.id.action_signOutFragment_to_signInFragment)
        }
    }

    private fun onRegisterButtonClick() {
        binding.btnRegister.setOnClickListener {
            Utils.showDialog(requireContext() , "Signing you...")
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if(password == confirmPassword){
                    signUpUser(email,password)
                }
                else{
                    Utils.showToast(requireContext(),"Passwords are not matching")
                }
            }
            else{
                Utils.hideDialog()
                Utils.showToast(requireContext(),"Empty Fields are not allowed")
            }
            }
    }

    private fun signUpUser(email: String, password: String) {
        val firebaseAuth =  FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
        firebaseAuth.addOnSuccessListener {
            Utils.hideDialog()
            Utils.showToast(requireContext() , "Signed Up , please login now")
            findNavController().navigate(R.id.action_signOutFragment_to_signInFragment)
        }
    }


}
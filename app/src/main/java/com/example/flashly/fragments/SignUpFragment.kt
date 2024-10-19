package com.example.flashly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.flashly.R
import com.example.flashly.databinding.FragmentLoginBinding
import com.example.flashly.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        init()
        setOnClickListner()
        return binding.root
    }

    private fun init() {
        navController = findNavController()
    }

    private fun setOnClickListner() {
        binding.signInHere.setOnClickListener {
           /* navController.navigate(R.id.action_signUp_fragment_to_login_fragment)*/
            val actionSignUpFragmentDirections = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            navController.navigate(actionSignUpFragmentDirections)
        }
    }
}
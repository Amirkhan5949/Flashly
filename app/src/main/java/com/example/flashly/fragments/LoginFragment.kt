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

class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        init()
        setOnCLickListner()
        return binding.root
    }

    private fun init() {
        navController = findNavController()
    }

    private fun setOnCLickListner() {
        binding.signUpHere.setOnClickListener {
            navController.navigate(R.id.action_login_fragment_to_signUp_fragment)
        }
    }
}
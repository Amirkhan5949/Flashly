package com.example.flashly.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.flashly.R
import com.example.flashly.databinding.FragmentSignUpBinding
import com.example.flashly.extension.ExtensionFunctions.isValidEmail
import com.example.flashly.extension.ExtensionFunctions.togglePasswordVisibility
import com.example.flashly.sealed.States
import com.example.flashly.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private val registerViewModel: RegisterViewModel by viewModels()
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var navController: NavController
    lateinit var progressDialog: ProgressDialog
    private var isPasswordVisible = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        init()
        setOnClickListener()
        observer()
        return binding.root
    }

    private fun observer() {
        lifecycleScope.launch {
            registerViewModel.register.collect {
                when (it) {
                    is States.Loading -> {
                        progressDialog.show()
                    }

                    is States.Success -> {
                        progressDialog.dismiss()
                        Toast.makeText(
                            context,
                            "you successfully registered.",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(R.id.action_signUp_fragment_to_login_fragment)
                    }

                    is States.Failure -> {
                        progressDialog.dismiss()
                        Toast.makeText(
                            context,
                            "error + ${it.toString()}",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                    else -> {}
                }
            }
        }
    }

    private fun init() {
        navController = findNavController()
        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
    }

    private fun setOnClickListener() {
        binding.ivEyeClosed.setOnClickListener {
            isPasswordVisible =
                binding.etPassword.togglePasswordVisibility(binding.ivEyeClosed, isPasswordVisible)
        }

        binding.ivConfirmEyeClosed.setOnClickListener {
            isPasswordVisible = binding.etConfirmPassword.togglePasswordVisibility(
                binding.ivConfirmEyeClosed,
                isPasswordVisible
            )
        }

        binding.signInHere.setOnClickListener {
            navController.navigate(R.id.action_signUp_fragment_to_login_fragment)
            /*val actionSignUpFragmentDirections =
                SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            navController.navigate(actionSignUpFragmentDirections)*/
        }

        binding.signInButton.setOnClickListener {
            if (validateInput()) {
                val hashmap = HashMap<String, String>().apply {
                    put("first_name", binding.etName.toString())
                    put("last_name", "khan")
                    put("country_code", "+91")
                    put("phone_no", "1424243543")
                    put("email", binding.etEmail.text.toString())
                    put("password", binding.etPassword.text.toString())
                    put("confirm_password", binding.etConfirmPassword.text.toString())
                }
                registerViewModel.getRegister(hashmap)
            }
        }
    }

    private fun validateInput(): Boolean {
        val userName = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()

        // Check if the username is empty
        if (userName.isEmpty()) {
            binding.etName.error = "Please enter your username"
            return false
        }

        // Check if the email is empty
        if (email.isEmpty()) {
            binding.etEmail.error = "Please enter your email"
            return false
        }

        if (!email.isValidEmail()) {
            binding.etEmail.error = "Please enter a valid email address"
            return false
        }
        // Check if the password is empty
        if (password.isEmpty()) {
            binding.etPassword.error = "Please enter a password"
            return false
        }

        // Check if the password length is less than 8 characters
        if (password.length < 8) {
            binding.etPassword.error = "Password should be at least 8 characters"
            return false
        }

        // Check if the confirm password is empty
        if (confirmPassword.isEmpty()) {
            binding.etConfirmPassword.error = "Please confirm your password"
            return false
        }

        // Check if password and confirm password are the same
        if (password != confirmPassword) {
            binding.etConfirmPassword.error = "Passwords do not match"
            return false
        }
        return true
    }

}
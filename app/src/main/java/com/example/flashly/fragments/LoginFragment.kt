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
import com.example.flashly.databinding.FragmentLoginBinding
import com.example.flashly.extension.ExtensionFunctions.isValidEmail
import com.example.flashly.sealed.States
import com.example.flashly.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController
    private val registerViewModel: RegisterViewModel by viewModels()
    lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        init()
        setOnCLickListner()
        observer()
        return binding.root
    }

    private fun observer() {
        lifecycleScope.launch {
            registerViewModel.login.collect { it ->
                when (it) {
                    is States.Loading -> {
                        progressDialog.show()
                    }

                    is States.Success -> {
                        progressDialog.dismiss()
                        Toast.makeText(context, it.data.message.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }

                    is States.Failure -> {
                        progressDialog.dismiss()
                        Toast.makeText(context, it.error.message.toString(), Toast.LENGTH_SHORT)
                            .show()
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

    private fun setOnCLickListner() {
        binding.signUpHere.setOnClickListener {
            navController.navigate(R.id.action_login_fragment_to_signUp_fragment)
        }
        binding.signInButton.setOnClickListener {
            if (validate()) {
                val hashMap = HashMap<String, String>().apply {
                    put("email", binding.etEmail.text.toString())
                    put("password", binding.etPassword.text.toString())
                }
                registerViewModel.getLogin(hashMap)
            }
        }
    }

    private fun validate(): Boolean {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty()) {
            binding.etEmail.error = "Please enter email"
            return false
        }
        if (!email.isValidEmail()) {
            binding.etEmail.error = "Please enter valid email"
            return false
        }
        if (password.isEmpty()) {
            binding.etPassword.error = "Please enter password"
            return false
        }
        if (password.length < 8) {
            binding.etPassword.error = "Password should be in 8 digits"
            return false
        }
        return true
    }
}
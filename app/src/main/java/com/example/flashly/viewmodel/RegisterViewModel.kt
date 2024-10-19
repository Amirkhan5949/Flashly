package com.example.flashly.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashly.data.RegisterRepo
import com.example.flashly.model.LoginResponse
import com.example.flashly.model.RegisterResponse
import com.example.flashly.sealed.States
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerRepo: RegisterRepo) : ViewModel() {
    private var _register = MutableStateFlow<States<RegisterResponse>?>(null)
    var register: StateFlow<States<RegisterResponse>?> = _register

    fun getRegister(hashMap: HashMap<String, String>) {
        viewModelScope.launch {
            registerRepo.getRegister(hashMap).collect {
                _register.value = it
            }
        }
    }

    private var _login = MutableStateFlow<States<LoginResponse>?>(null)
    var login : StateFlow<States<LoginResponse>?> = _login

    fun getLogin(hashMap: HashMap<String, String>){
        viewModelScope.launch {
            registerRepo.getLogin(hashMap).collect{
                _login.value = it
            }
        }
    }
}
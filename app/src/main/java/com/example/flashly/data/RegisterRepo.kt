package com.example.flashly.data

import com.example.flashly.model.LoginResponse
import com.example.flashly.model.RegisterResponse
import com.example.flashly.sealed.States
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRepo @Inject constructor(private val apiService: ApiService) {
    suspend fun getRegister(hashMap: HashMap<String, String>): kotlinx.coroutines.flow.Flow<States<RegisterResponse>?> =
        flow<States<RegisterResponse>?> {
            emit(States.Loading())
            emit(States.Success(apiService.getRegister(hashMap)))
        }.catch {e->
            emit(States.Failure(e))
        }

    suspend fun getLogin(hashMap: HashMap<String, String>) : Flow<States<LoginResponse>> =
        flow {
            emit(States.Loading())
            emit(States.Success(apiService.getLogin(hashMap)))
        }.catch { e->
            emit(States.Failure(e))
        }
}
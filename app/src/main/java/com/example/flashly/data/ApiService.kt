package com.example.flashly.data

import com.example.flashly.model.LoginResponse
import com.example.flashly.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("userRegister")
    suspend fun getRegister(@Body hashMap: HashMap<String,String>) : RegisterResponse

    @POST("userLogin")
    suspend fun getLogin(@Body hashMap : HashMap<String,String>) : LoginResponse
}
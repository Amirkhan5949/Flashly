package com.example.flashly.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterCeptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val reqBuilder = chain.request().newBuilder()
        return chain.proceed(reqBuilder.build())
    }
}
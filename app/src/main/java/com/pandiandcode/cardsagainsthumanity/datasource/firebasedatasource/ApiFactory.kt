package com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory {

    private const val BASE_URL = "https://carsagainstperra-d8ea.restdb.io/rest/"
    private const val API_KEY = "x-apikey"

    // Creating Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor { chain ->
        val newHeader = chain.request()
            .headers()
            .newBuilder()
            .add(API_KEY, "0ac361fd985eea54cd349b5bcd0640a7890ba")
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .headers(newHeader)
            .build()

        chain.proceed(newRequest)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    private fun retrofit() = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    fun getApi() = retrofit().create(CahApi::class.java)

}
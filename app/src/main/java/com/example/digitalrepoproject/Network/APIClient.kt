package com.example.digitalrepoproject.Network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {

     val BASE_URL :String ="https://api.github.com/users/JakeWharton/"


    fun getRetrofitInstance(): APIServices {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIServices::class.java)

    }

    private fun getClient():OkHttpClient{
          val NETWORK_CALL_TIMEOUT = 60
        return OkHttpClient.Builder()
            .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
    }
}
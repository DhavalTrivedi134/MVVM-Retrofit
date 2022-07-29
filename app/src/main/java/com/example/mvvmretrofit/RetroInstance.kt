package com.example.mvvmretrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetroInstance {
    companion object {
        private var retroIns: Retrofit? = null
        private var instance: ApiInterface? = null
        private var data_url = "https://quotable.io/"

        private fun retroBuilder(): Retrofit {

            if (retroIns == null) {
                return Retrofit.Builder()
                    .baseUrl(data_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retroIns as Retrofit
        }

        @Synchronized
        fun getInstance(): ApiInterface {
            if (instance == null) {
                instance = retroBuilder().create(ApiInterface::class.java)
            }
            return instance as ApiInterface
        }
    }
}
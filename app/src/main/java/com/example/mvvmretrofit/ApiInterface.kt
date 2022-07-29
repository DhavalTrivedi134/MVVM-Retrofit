package com.example.mvvmretrofit

import com.example.mvvmretrofit.model.Quote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("quotes")
    suspend fun getQuotes(@Query("page") page:Int) :Response<Quote>
}
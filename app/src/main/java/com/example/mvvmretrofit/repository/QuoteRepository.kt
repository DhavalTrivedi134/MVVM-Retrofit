package com.example.mvvmretrofit.repository

import com.example.mvvmretrofit.ApiInterface

class QuoteRepository(private val apiInterface: ApiInterface) {

    suspend fun getQuote(page:Int) = apiInterface.getQuotes(page)
}


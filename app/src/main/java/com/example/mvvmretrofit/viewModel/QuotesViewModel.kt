package com.example.mvvmretrofit.viewModel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmretrofit.model.Quote
import com.example.mvvmretrofit.repository.QuoteRepository
import kotlinx.coroutines.launch


class QuotesViewModel(private val repository: QuoteRepository):ViewModel() {

    private val mutableLiveData = MutableLiveData<Quote>()

    val quotes: LiveData<Quote>  get() = mutableLiveData


    init {
        viewModelScope.launch {
            val result = repository.getQuote(1)
            val resultData = result.body()
            if (resultData != null){
                mutableLiveData.postValue(result.body())

            }
        }
    }
}
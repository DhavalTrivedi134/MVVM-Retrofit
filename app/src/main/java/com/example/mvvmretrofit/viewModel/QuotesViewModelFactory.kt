package com.example.mvvmretrofit.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofit.repository.QuoteRepository

class QuotesViewModelFactory(private val repository: QuoteRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}
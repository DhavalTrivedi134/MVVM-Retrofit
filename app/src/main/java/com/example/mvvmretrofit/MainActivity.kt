package com.example.mvvmretrofit

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmretrofit.adapter.QuotesAdapter
import com.example.mvvmretrofit.databinding.ActivityMainBinding
import com.example.mvvmretrofit.repository.QuoteRepository
import com.example.mvvmretrofit.viewModel.QuotesViewModel
import com.example.mvvmretrofit.viewModel.QuotesViewModelFactory

class MainActivity : AppCompatActivity() {
    private var resultList = ArrayList<com.example.mvvmretrofit.model.Result>()
    private var quotesAdapter: QuotesAdapter = QuotesAdapter(resultList)
    private lateinit var quotesViewModel: QuotesViewModel
    private var _binding:ActivityMainBinding? = null
    private val binding get() = _binding
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this,R.layout.activity_main)



        val apiInterface = RetroInstance.getInstance()
        val repository = QuoteRepository(apiInterface)

        binding?.progress?.visibility = View.VISIBLE

        if (checkForInternet()){
            quotesViewModel = ViewModelProvider(this,
                QuotesViewModelFactory(repository = repository))[QuotesViewModel::class.java]
            quotesViewModel.quotes.observe(this) {
                resultList.addAll(it.results)
                Log.e("Data", resultList.toString())
                binding?.rv?.layoutManager = GridLayoutManager(this, 1)
                binding?.rv?.adapter = quotesAdapter
                quotesAdapter.notifyDataSetChanged()
                binding?.progress?.visibility = View.GONE
            }
        }else{
            binding?.progress?.visibility = View.GONE
            Toast.makeText(this@MainActivity, "Please Check Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }



   private fun checkForInternet(): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection

        // Returns a Network object corresponding to
        // the currently active default data network.
        val network = connectivityManager.activeNetwork ?: return false

        // Representation of the capabilities of an active network.
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            // Indicates this network uses a Wi-Fi transport,
            // or WiFi has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            // Indicates this network uses a Cellular transport. or
            // Cellular has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            // else return false
            else -> false
        }
    }
}
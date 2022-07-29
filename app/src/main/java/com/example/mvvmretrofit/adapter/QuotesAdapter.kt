package com.example.mvvmretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmretrofit.databinding.RecyclerApiDataBinding
import com.example.mvvmretrofit.model.Quote

class QuotesAdapter(val quoteList:ArrayList<com.example.mvvmretrofit.model.Result>):RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder>() {
    class QuoteViewHolder(private val rvBinding:RecyclerApiDataBinding):RecyclerView.ViewHolder(rvBinding.root) {
        val tv1 = rvBinding.data1
        val tv2 = rvBinding.data2
        val tv3 = rvBinding.data3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = RecyclerApiDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.apply {
            tv1.text = quoteList[position]._id
            tv2.text = quoteList[position].author
            tv3.text = quoteList[position].content
        }
    }

    override fun getItemCount(): Int {
        return quoteList.size
    }
}
package com.deepak.demo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deepak.demo.interfaces.RecyclerViewClickInterface
import com.deepak.demo.products.Product
import com.deepak.demo.products.ProductRecyclerAdapter
import com.deepak.demo.products.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RecyclerViewClickInterface {

    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var productRecyclerAdapter: ProductRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var dataLimit = 10
    private var productsList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewByIds()
        observeLiveData()

    }

    private fun findViewByIds() {
        // all the view are find here

        recyclerView = findViewById(R.id.recycler_view)
        progressBar = findViewById(R.id.progress_circular)

        // set the adapter and passing emptylist
        productRecyclerAdapter = ProductRecyclerAdapter(productsList, this)
        recyclerView.adapter = productRecyclerAdapter
    }

    private fun observeLiveData() {
        // observing the live data and adding all the data to our arrayList
        viewModel.items.observe(this) {
            progressBar.visibility = View.GONE
            if (productsList.size > 0) productsList.clear()
            productsList.addAll(it.products)
            Log.d("TESTING", "data = ${it.products}")

            // notify the adapter that for the changes
            productRecyclerAdapter.notifyDataSetChanged()
        }

        // implementing the pagination
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= dataLimit
                ) {
                    // here I plus 10 when user scroll
                    dataLimit = dataLimit + 10
                    fetchData(dataLimit)
                }
            }
        })

        // initial I get 10 data for first time
        viewModel.fetchProductData(10)
    }

    // calling the fun from viewmodel to call out api
    private fun fetchData(limit: Int) {
        progressBar.visibility = View.VISIBLE
        viewModel.fetchProductData(limit)
    }


    override fun onProductClick(position: Int) {
        val products = productsList[position]
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("id", products.id.toString())
        intent.putExtra("name", products.title)
        intent.putExtra("desc", products.description)
        intent.putExtra("thumbnail", products.thumbnail)
        startActivity(intent)
    }

}
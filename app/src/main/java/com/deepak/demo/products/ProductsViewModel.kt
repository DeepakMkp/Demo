package com.deepak.demo.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor (private val productsRepo: ProductsRepo) : ViewModel() {

    private val _productsData = MutableLiveData<ProductsData>()
    val items: LiveData<ProductsData> = _productsData

    fun fetchProductData(pageNo: Int) {

        viewModelScope.launch {
            _productsData.value = productsRepo.getProducts(pageNo)
        }
    }
}
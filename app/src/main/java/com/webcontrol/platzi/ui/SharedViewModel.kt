package com.webcontrol.platzi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webcontrol.platzi.core.Resource
import com.webcontrol.platzi.domain.GetProductsUseCase
import com.webcontrol.platzi.domain.model.ProductModel
import com.webcontrol.platzi.ui.adapters.ProductListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel(), ProductListener {

    private val _loadingVisibility = MutableLiveData<Boolean>()
    val loadingVisibility: LiveData<Boolean> = _loadingVisibility

    private val _products = MutableLiveData<List<ProductModel>>()
    val products: LiveData<List<ProductModel>> = _products

    init {
        onCreate()
    }

    private fun onCreate() {
        getProductsUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Loading -> showLoading()
                is Resource.Error -> {
                    hideLoading()
                }
                is Resource.Success ->{
                    _products.value = result.data!!
                    hideLoading()
                }

            }
        }.launchIn(viewModelScope)
    }

    private fun hideLoading() {
        _loadingVisibility.value = false
    }

    private fun showLoading() {
        _loadingVisibility.value = true
    }

    override fun onProductClicked(product: ProductModel) {

    }

}
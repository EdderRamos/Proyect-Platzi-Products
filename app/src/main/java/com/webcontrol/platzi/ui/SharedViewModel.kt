package com.webcontrol.platzi.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webcontrol.platzi.core.Constant
import com.webcontrol.platzi.core.Resource
import com.webcontrol.platzi.domain.GetProductsUseCase
import com.webcontrol.platzi.domain.OrderByPriceUseCase
import com.webcontrol.platzi.domain.SearchProductUseCase
import com.webcontrol.platzi.domain.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.function.BinaryOperator
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val orderByPriceUseCase: OrderByPriceUseCase,
    private val searchProductUseCase: SearchProductUseCase
) : ViewModel() {

    private val _loadingVisibility = MutableLiveData<Boolean>()
    val loadingVisibility: LiveData<Boolean> = _loadingVisibility

    private val _products = MutableLiveData<List<ProductModel>>()
    val products: LiveData<List<ProductModel>> = _products

    private val _productSelected = MutableLiveData<ProductModel>()
    val productSelected: LiveData<ProductModel> = _productSelected

    private val _isOrderedByLowerPrice = MutableLiveData<Boolean>()
    val isOrderedByLowerPrice: LiveData<Boolean> = _isOrderedByLowerPrice

    private val _showMessageError = MutableLiveData<String>()
    val showMessageError: LiveData<String> = _showMessageError
    init {
        onCreate()
    }

    fun onCreate() {
        getProductsUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Loading -> showLoading()
                is Resource.Error -> {
                    hideLoading()
                    _showMessageError.value = result.message?: Constant.MSG_ERROR_INTERN
                }

                is Resource.Success -> {
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

    @SuppressLint("NullSafeMutableLiveData")
    fun changeOrderBySelected(ascending: Boolean) {
        viewModelScope.launch {
            _products.value = orderByPriceUseCase(ascending, _products.value)
            _isOrderedByLowerPrice.value = ascending
        }
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun searchProducts(query: String) {
        viewModelScope.launch {
            _products.value = searchProductUseCase(query)
            changeOrderBySelected(_isOrderedByLowerPrice.value?: false)
        }
    }

    fun changeProductSelected(product: ProductModel) {
        _productSelected.value = product
    }

}
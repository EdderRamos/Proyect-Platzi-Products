package com.webcontrol.platzi.ui.adapters

import com.webcontrol.platzi.R
import com.webcontrol.platzi.core.ui.BaseAdapter
import com.webcontrol.platzi.databinding.ViewProductItemBinding
import com.webcontrol.platzi.domain.model.ProductModel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class ProductsAdapter(
    data: List<ProductModel>,
    private val productListener: ProductListener,
) : BaseAdapter<ViewProductItemBinding, ProductModel>(data) {

    override val layoutId: Int = R.layout.view_product_item

    override fun bind(binding: ViewProductItemBinding, item: ProductModel) {
        val images = mutableListOf<CarouselItem>()
        item.images.forEach {
            images.add(CarouselItem(it, item.title))
        }

        binding.apply {
            product = item
            listener = productListener
            carousel.setData(images)
            carousel.autoWidthFixing = true
            carousel.carouselListener = object : CarouselListener {
                override fun onClick(position: Int, carouselItem: CarouselItem) {
                    productListener.onProductClicked(item)
                }
            }
            executePendingBindings()
        }
    }
}

interface ProductListener {
    fun onProductClicked(product: ProductModel)
}
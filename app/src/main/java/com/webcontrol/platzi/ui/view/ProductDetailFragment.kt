package com.webcontrol.platzi.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import com.webcontrol.platzi.R
import com.webcontrol.platzi.databinding.FragmentProductDetailBinding
import com.webcontrol.platzi.ui.SharedViewModel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class ProductDetailFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
        exitTransition = MaterialFadeThrough()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initUI()
        return binding.root
    }

    private fun initUI() {
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        viewModel.productSelected.observe(viewLifecycleOwner){
            val images = mutableListOf<CarouselItem>()
            it.images.forEach {
                images.add(CarouselItem(it))
            }
            binding.carousel.setData(images)
        }
    }

    private fun initListeners() {
        binding.btnBuy.setOnClickListener{
            Toast.makeText(requireContext(), "COMPRADO", Toast.LENGTH_LONG).show()
        }
    }

    companion object {

    }
}
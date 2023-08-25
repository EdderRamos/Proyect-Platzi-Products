package com.webcontrol.platzi.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.webcontrol.platzi.R
import com.webcontrol.platzi.databinding.FragmentProductsBinding
import com.webcontrol.platzi.ui.SharedViewModel
import com.webcontrol.platzi.ui.adapters.ProductsAdapter


class ProductsFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_products, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setupSpinnerOptions()
        initUI()
        return binding.root
    }

    private fun initUI() {
        initObservers()
        initRecyclerView()
        initListeners()
    }

    private fun initListeners() {
        with(binding){
            spinnerOptions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val options: Array<String> = resources.getStringArray(R.array.spinner_options)
                    when(options[position]){
                        options[0] -> {

                        }
                        options[1] -> {

                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.adapter = ProductsAdapter(listOf(), viewModel)
    }

    private fun initObservers() {
    }

    companion object {

    }

    private fun setupSpinnerOptions() {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_options, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerOptions.adapter = adapter
    }
}
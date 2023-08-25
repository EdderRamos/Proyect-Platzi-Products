package com.webcontrol.platzi.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.search.SearchView.TransitionState
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import com.webcontrol.platzi.R
import com.webcontrol.platzi.core.Constant
import com.webcontrol.platzi.core.dialog.ErrorDialog
import com.webcontrol.platzi.databinding.FragmentProductsBinding
import com.webcontrol.platzi.domain.model.ProductModel
import com.webcontrol.platzi.ui.SharedViewModel
import com.webcontrol.platzi.ui.adapters.ProductListener
import com.webcontrol.platzi.ui.adapters.ProductsAdapter


class ProductsFragment : Fragment(), ProductListener {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentProductsBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_products, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initUI()
        return binding.root
    }

    private fun initUI() {
        initObservers()
        initRecyclerViews()
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            spinnerOptions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val options: Array<String> = resources.getStringArray(R.array.spinner_options)
                    viewModel!!.changeOrderBySelected(position == 0)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

            searchView
                .editText
                .setOnEditorActionListener { v, actionId, event ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    false
                }

            searchView.addTransitionListener { searchView, previousState, newState ->
                if (newState === TransitionState.HIDDEN) {
                    viewModel!!.searchProducts(searchBar.text.toString())
                }
            }

            searchView.editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewModel!!.searchProducts(p0.toString())
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })

        }
    }

    private fun initRecyclerViews() {
        binding.adapter = ProductsAdapter(listOf(), this)
        setupSpinnerOptions()
    }

    private fun initObservers() {
        viewModel.showMessageError.observe(viewLifecycleOwner) { message ->
            ErrorDialog.create(Constant.MSG_ERROR_GET_PRODUCTS, description = message,
                negativeAction = ErrorDialog.Action(getString(R.string.txt_retry)) {
                    it.dismiss()
                    viewModel.onCreate()
                },
                isDialogCancelable = false
            ).show(parentFragmentManager, "Error")
        }
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

    private fun onNavigateProductDetail() {
        findNavController().navigate(R.id.action_productFragment_to_productDetailFragment)
    }

    override fun onProductClicked(product: ProductModel) {
        viewModel.changeProductSelected(product)
        onNavigateProductDetail()
    }
}
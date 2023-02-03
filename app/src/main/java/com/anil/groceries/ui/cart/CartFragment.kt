package com.anil.groceries.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.anil.groceries.R
import com.anil.groceries.databinding.FragmentCartBinding
import com.anil.groceries.model.Product
import com.anil.groceries.ui.base.BaseFragment
import com.anil.groceries.utils.ProductUtils.Companion.addProductToCart
import timber.log.Timber

class CartFragment : BaseFragment(), CartListAdapterListener {
    private lateinit var binding: FragmentCartBinding
    private lateinit var adapter: CartListAdapter
    private val viewModel: CartViewModel by viewModels()

    companion object {
        fun newInstance(bundle: Bundle?) = CartFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CartListAdapter(this)
        binding.recyclerView.adapter = adapter

        viewModel.cartProducts.observe(viewLifecycleOwner) {
            Timber.e("products:- $it")
            binding.emptyMessage.isVisible = it.isEmpty()

            adapter.submitList(null)
            adapter.submitList(it)

            renderPrice(it)

        }
    }

    private fun renderPrice(products: List<Product>?){
        products?.let {
            var sum = 0
            for (i in products.indices){
                val totalvalue = products[i].cartQuantity * products[i].price

                sum += totalvalue
            }
            Timber.e("The sum is $sum")
            binding.btnCheckout.text = getString(R.string.checkout, sum)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onPlusClicked(product: Product) {
        viewModel.update(addProductToCart(product))
    }

    override fun onRemoveClicked(product: Product) {
        product.isAddedCart = false
        product.cartQuantity = 0
        viewModel.update(product)
        toast("Item is removed from the Cart")
    }

    override fun onMinusClicked(product: Product) {
        if (product.cartQuantity > 1) {
            product.cartQuantity = product.cartQuantity - 1
        } else {
            product.isAddedCart = false
            product.cartQuantity = 0
        }
        viewModel.update(product)
    }
}




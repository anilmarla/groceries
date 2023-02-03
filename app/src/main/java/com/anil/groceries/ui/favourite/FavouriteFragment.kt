package com.anil.groceries.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.anil.groceries.databinding.FragmentFavouriteBinding
import com.anil.groceries.model.Product
import com.anil.groceries.ui.base.BaseFragment
import com.anil.groceries.ui.product_details.ProductDetailsViewModel
import com.anil.groceries.ui.productlist.ProductListViewModel
import timber.log.Timber

class FavouriteFragment : BaseFragment(), FavouritesAdapterListener {
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var adapter: FavouritesAdapter
    private var productId: String? = null
    private val viewModel: FavouriteViewModel by viewModels()
    private val viewModel2: ProductDetailsViewModel by viewModels()


    companion object {
        fun newInstance() = FavouriteFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FavouritesAdapter(this)
        binding.recyclerView.adapter = adapter

        viewModel.favouriteProduct.observe(viewLifecycleOwner) {
            Timber.e("products:- $it")
            binding.emptyMessage.isVisible = it.isEmpty()
            adapter.submitList(null)
            adapter.submitList(it)



        }
    }

    override fun onIconClicked(product: Product) {
        TODO("Not yet implemented")
    }
}
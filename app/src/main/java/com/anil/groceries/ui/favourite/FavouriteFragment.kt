package com.anil.groceries.ui.favourite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.anil.groceries.databinding.FragmentFavouriteBinding
import com.anil.groceries.model.Product
import com.anil.groceries.ui.base.BaseFragment
import com.anil.groceries.ui.productDetails.ProductDetailsActivity
import timber.log.Timber

class FavouriteFragment : BaseFragment(), FavouritesAdapterListener {
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var adapter: FavouritesAdapter
    private val viewModel: FavouriteViewModel by viewModels()

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
        startActivity(
            Intent(activity, ProductDetailsActivity::class.java).apply {
                putExtra("product_id", product.id)
            }
        )
    }
}

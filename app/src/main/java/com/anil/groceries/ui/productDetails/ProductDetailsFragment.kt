package com.anil.groceries.ui.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.anil.groceries.R
import com.anil.groceries.databinding.FragmentProductDetailsBinding
import com.anil.groceries.model.Product
import com.anil.groceries.network.Result
import com.anil.groceries.ui.base.BaseFragment
import com.anil.groceries.ui.productDetails.slideProductImage.SlideImageAdapter
import com.anil.groceries.ui.productlist.ProductListViewModel
import com.anil.groceries.utils.ProductUtils
import com.anil.groceries.utils.ProductUtils.Companion.addFavouriteToCart
import com.anil.groceries.utils.ProductUtils.Companion.formatPrice
import com.bumptech.glide.Glide
import timber.log.Timber

class ProductDetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var adapter: SlideImageAdapter
    var product: Product? = null
    private var productId: String? = null
    private val viewModel: ProductDetailsViewModel by viewModels()
    private val productListviewModel: ProductListViewModel by viewModels()

    companion object {
        fun newInstance(bundle: Bundle?) =
            ProductDetailsFragment().apply {
                arguments = bundle
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getString("product_id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productId?.let {
            productListviewModel.getProduct(it)
        }

        productListviewModel.productRemote.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Result.Success -> {
                    binding.progressBar.isVisible = false
                    Timber.e("Product details ${result.responseData}")
                }
                is Result.Error -> {
                    binding.progressBar.isVisible = false
                }

            }

        }

        productId?.let {
            viewModel.setProductId(it)
        }

        viewModel.product.observe(viewLifecycleOwner) {
            product = it

            binding.name.text = it.title
            binding.itemPrice.text = formatPrice(context, it.price)
            binding.itemQuantity.text = it.cartQuantity.toString()

            if (it.isAddedFavourite) {
                binding.btnFavourite.setImageResource(R.drawable.ic_favourite_filled)

            } else {
                binding.btnFavourite.setImageResource(R.drawable.ic_favourite)
            }

           /* Glide.with(binding.root.context)
                .load(it.thumbnail)
                .fitCenter()
                .into(binding.image)*/

            product?.let {
                activity?.title = it.title
            }

            it.images?.let{images ->
                adapter = SlideImageAdapter(images)
                binding.viewpager.adapter = adapter
            }

            binding.btnAddCart.setOnClickListener {
                product?.let {
                    productListviewModel.update(ProductUtils.addProductToCart(it))
                    toast("item is added to cart")
                }
            }
            binding.btnItemAdd.setOnClickListener {
                product?.let {
                    productListviewModel.update(ProductUtils.addProductToCart(it))
                    toast("item is added to cart")
                }
            }

            binding.btnItemMinus.setOnClickListener {
                product?.let {
                    productListviewModel.update(ProductUtils.minusProduct(it))
                    toast("item is added to cart")
                }
            }

            binding.btnFavourite.setOnClickListener {
                product?.let {
                    viewModel.update(addFavouriteToCart(it))
                }
            }
        }
    }
}
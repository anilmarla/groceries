package com.anil.groceries.ui.productlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.anil.groceries.R
import com.anil.groceries.databinding.FragmentProductListBinding
import com.anil.groceries.model.Product
import com.anil.groceries.network.Result
import com.anil.groceries.ui.additem.AddItemActivity
import com.anil.groceries.ui.base.BaseFragment
import com.anil.groceries.ui.productDetails.ProductDetailsActivity
import com.anil.groceries.utils.MarginItemDecoration
import com.anil.groceries.utils.ProductUtils.Companion.addProductToCart
import com.google.gson.Gson
import timber.log.Timber

class ProductListFragment : BaseFragment(), ProductListAdapterListener {
    private lateinit var binding: FragmentProductListBinding
    private lateinit var adapter: ProductListAdapter
    private var categoryId: String? = null
    //private var isAdded: List<Product>? = null
    private var categoryName: String? = null
    private val viewModel: ProductListViewModel by viewModels()
    companion object {
        fun newInstance(bundle: Bundle?) = ProductListFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            startActivity(Intent(activity, AddItemActivity::class.java).apply {
                putExtra("category_id", categoryId)
            })
        }
        adapter = ProductListAdapter(this, context)
        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        // space b/w items
        binding.recyclerView.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_small), 2)
        )

        viewModel.getProducts()

        viewModel.productsRemote.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Result.Success -> {
                    binding.progressBar.isVisible = false

                    //val products = result.responseData.products
                    //adapter.submitList(products)
                    //Timber.e("Products $products")
                }
                is Result.Error -> {
                    binding.progressBar.isVisible = false
                }

            }

        }

        viewModel.products.observe(viewLifecycleOwner) {
            Timber.e("Products $it")
            binding.emptyMessage.isVisible = it.isEmpty()
            adapter.submitList(it)
        }

        // grid layout
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        // space b/w items
        binding.recyclerView.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_small), 2)
        )

        Timber.e("categoryId $categoryId")
        Timber.e("CategoryName $categoryName")

        activity?.title = categoryName

        categoryId?.let {
            viewModel.setCategoryId(it)
            //viewModel.getProducts(it)
        }
        /*isAdded?.let {
            viewModel.sortByName()
        }*/

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryId = it.getString("category_id")
            categoryName = it.getString("category_name")
        }
    }

    override fun onIconClicked(product: Product) {
        val gson = Gson()
        val productJson = gson.toJson(product)
        Timber.e("Product json $productJson")

        val json =
            "{\"cartQuantity\":10,\"categoryId\":\"4615dadc-d418-4d23-a2e7-8c17bf8c74d1\",\"id\":\"6e6c7804-59dd-4ce9-bcd9-df210889bd99\",\"image\":\"content://media/external_primary/images/media/1000100968\",\"isAddedCart\":true,\"isAddedFavourite\":true,\"name\":\"Watermelon Json\",\"price\":100}"
        val p = gson.fromJson(json, Product::class.java)
        Timber.e("Product obj $p")


        startActivity(
            Intent(activity, ProductDetailsActivity::class.java).apply {
                putExtra("product_id", product.id)
            }
        )
    }

    override fun onAddBtnClicked(product: Product) {
        viewModel.update(addProductToCart(product))
        toast("item is added to cart")
    }
}
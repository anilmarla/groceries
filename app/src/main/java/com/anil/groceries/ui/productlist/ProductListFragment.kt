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
import com.anil.groceries.databinding.FragmentCategoryListBinding
import com.anil.groceries.model.Product
import com.anil.groceries.ui.additem.AddItemActivity
import com.anil.groceries.ui.base.BaseFragment
import com.anil.groceries.ui.product_details.ProductDetailsActivity
import com.anil.groceries.utils.MarginItemDecoration
import com.anil.groceries.utils.ProductUtils.Companion.addProductToCart
import timber.log.Timber

class ProductListFragment : BaseFragment(), ProductListAdapterListener {
    private lateinit var binding: FragmentCategoryListBinding
    private lateinit var adapter: ProductListAdapter
    private var categoryId: String? = null
    private var categoryName: String? = null

    companion object {
        fun newInstance(bundle: Bundle?) = ProductListFragment().apply {
            arguments = bundle
        }
    }

    private val viewModel: ProductListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryListBinding.inflate(inflater, container, false)
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
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryId = it.getString("category_id")
            categoryName = it.getString("category_name")
        }
    }

    override fun onIconClicked(product: Product) {
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
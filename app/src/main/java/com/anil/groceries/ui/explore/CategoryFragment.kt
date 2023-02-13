package com.anil.groceries.ui.explore

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
import com.anil.groceries.network.Result
import com.anil.groceries.ui.addCategory.AddCategoryActivity
import com.anil.groceries.ui.addNewProductWithCategory.AddNewProductWithCategoryActivity
import com.anil.groceries.ui.base.BaseFragment
import com.anil.groceries.ui.productlist.ProductListActivity
import com.anil.groceries.utils.MarginItemDecoration
import timber.log.Timber

class CategoryFragment : BaseFragment(), CategoryListAdapterListener {
    private lateinit var binding: FragmentCategoryListBinding
    private lateinit var adapter: CategoriesAdapter
    private val viewModel: CategoryViewModel by viewModels()

    companion object {
        fun newInstance() = CategoryFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            launchAddCategory()
        }
        binding.fabForProduct.setOnClickListener {
            launchAddProductWithCategory()

        }
        renderList()

        // get posts
        viewModel.getPosts()

        // observe posts
        viewModel.posts.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Result.Success -> {
                    binding.progressBar.isVisible = false

                    val posts = result.responseData
                    Timber.e("Posts $posts")
                }
                is Result.Error -> {
                    binding.progressBar.isVisible = false
                }
            }
        }
    }


    private fun renderList() {
        adapter = CategoriesAdapter(this)
        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        // space b/w items
        binding.recyclerView.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_small), 2)
        )

        viewModel.categories.observe(viewLifecycleOwner) {
            binding.emptyMessage.isVisible = it.isEmpty()
            adapter.submitList(it)
        }
    }

    private fun launchAddCategory() {
        val intent = Intent(activity, AddCategoryActivity::class.java)
        startActivity(intent)
    }

    private fun launchAddProductWithCategory() {
        val intent = Intent(activity, AddNewProductWithCategoryActivity::class.java)
        startActivity(intent)
    }

    override fun onCardClicked(categoryId: String, name: String) {
        val intent = Intent(activity, ProductListActivity::class.java).apply {
            putExtra("category_id", categoryId)
            putExtra("category_name", name)
        }
        startActivity(intent)
    }

}
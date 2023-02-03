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
import com.anil.groceries.ui.add_category.AddCategoryActivity
import com.anil.groceries.ui.base.BaseFragment
import com.anil.groceries.ui.productlist.ProductListActivity
import com.anil.groceries.utils.MarginItemDecoration

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
        renderList()
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


    override fun onCardClicked(categoryId: String, name: String) {
        val intent = Intent(activity, ProductListActivity::class.java).apply {
            putExtra("category_id", categoryId)
            putExtra("category_name", name)
        }
        startActivity(intent)
    }

}
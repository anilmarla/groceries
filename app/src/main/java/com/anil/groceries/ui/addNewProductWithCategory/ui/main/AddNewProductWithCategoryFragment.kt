package com.anil.groceries.ui.addNewProductWithCategory.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anil.groceries.R
import com.anil.groceries.databinding.FragmentAddNewProductWithCategoryBinding
import com.anil.groceries.model.Category
import com.anil.groceries.ui.addNewProductWithCategory.CategoriesDropDownAdapter
import com.anil.groceries.ui.explore.CategoryViewModel

class AddNewProductWithCategoryFragment : Fragment() {
    private lateinit var binding: FragmentAddNewProductWithCategoryBinding
    private val categoryViewModel: CategoryViewModel by viewModels()
    private var categoriesAdapter: CategoriesDropDownAdapter? = null
    private var categoryId: String? = null

    companion object {
        fun newInstance() = AddNewProductWithCategoryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewProductWithCategoryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryViewModel.categories.observe(viewLifecycleOwner) {
            initIncomeTypeDropDown(it)

            binding.btnAddProduct.setOnClickListener{


                val name = binding.inputProductName.text.toString()
                val price = binding.inputProductPrice.text.toString()

                if (name.isBlank()) {
                    binding.inputProductName.error = "Please enter product name"
                    binding.inputProductName.requestFocus()
                    return@setOnClickListener
                }
                if (price.isBlank()) {
                    binding.inputProductPrice.error = "Please input product price "
                    binding.inputProductPrice.requestFocus()
                    return@setOnClickListener
                }
                if (categoryId.isNullOrBlank()) {
                    binding.category.error = "Enter the product category "
                    binding.category.requestFocus()
                }
            }
        }
    }

    private fun initIncomeTypeDropDown(categories: List<Category>) {
        context?.let {
            categoriesAdapter =
                CategoriesDropDownAdapter(it, R.layout.list_item_drop_down, categories)
            binding.categoryType.setAdapter(categoriesAdapter)

            binding.categoryType.onItemClickListener =
                AdapterView.OnItemClickListener { p0, p1, position, p3 ->
                    val category: Category = p0?.getItemAtPosition(position) as Category

                    binding.categoryType.setText(category.name, false)
                    categoryId = category.id

                }
        }
    }
}
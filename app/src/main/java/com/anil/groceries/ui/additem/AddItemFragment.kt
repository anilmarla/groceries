package com.anil.groceries.ui.additem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.anil.groceries.R
import com.anil.groceries.databinding.FragmentAddItemBinding
import com.anil.groceries.model.Product
import com.anil.groceries.ui.base.BaseFragment
import timber.log.Timber

class AddItemFragment : BaseFragment() {
    private lateinit var binding: FragmentAddItemBinding
    private var categoryId: String? = null

    companion object {

        fun newInstance(bundle: Bundle?) = AddItemFragment().apply {
            arguments = bundle
        }
    }

    private val viewModel: AddItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddItemBinding.inflate(inflater, container, false)


//        initIncomeTypeDropDown()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        categoryId = getString("categoryId", categoryId)

        // product = intent.getParcelableExtra("product")


        binding.apply {
            btnAddProduct.setOnClickListener {
                val name = inputProductName.text.toString()
                val price = inputProductPrice.text.toString()
                val image = inputProductImage.text.toString()

                if (name.isBlank()) {
                    binding.inputProductName.error = "Please input product name "
                    binding.inputProductName.requestFocus()
                    return@setOnClickListener
                }

                if (price.isBlank()) {
                    binding.inputProductPrice.error = "Please input product price "
                    binding.inputProductPrice.requestFocus()
                    return@setOnClickListener
                }
                if (image.isBlank()) {
                    binding.inputProductImage.error = "Please input product image "
                    binding.inputProductImage.requestFocus()
                    return@setOnClickListener
                }

                binding.inputProductName.error = null
                binding.inputProductPrice.error = null
                binding.inputProductImage.error = null


                //if (product == null) {
                    categoryId?.let {
                        viewModel.addProduct(
                            name = name,
                            price = price.toInt(),
                            image = image,
                            categoryId = it
                        )
                        toast(getString(R.string.product_added))
                        Timber.e("the product is $name ")
                        Timber.e("price is $price ")
                        activity?.finish()
                    }
                //}
            }
        }
    }


    /*  private fun initIncomeTypeDropDown() {
          val items = listOf(getString(R.string.fruits_vegetables), getString(R.string.fruits_vegetables), getString(R.string.fruits_vegetables))
          val adapter = ArrayAdapter(this, R.layout.list_item_drop_down, items)
          binding.categoryType.setAdapter(adapter)
      }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryId = it.getString("category_id")
        }
    }
}
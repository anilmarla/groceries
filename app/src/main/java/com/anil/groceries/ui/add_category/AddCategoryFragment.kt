package com.anil.groceries.ui.add_category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.anil.groceries.R
import com.anil.groceries.databinding.FragmentAddCategoryBinding
import com.anil.groceries.model.Category
import com.anil.groceries.ui.base.BaseFragment

class AddCategoryFragment : BaseFragment() {
    private lateinit var binding: FragmentAddCategoryBinding
    private val category: Category? = null
    private val viewModel: AddCategoryViewModel by viewModels()


    companion object {
        fun newInstance() = AddCategoryFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnAddCategory.setOnClickListener {
                val name = inputCategoryName.text.toString()
                val image = inputCategoryImage.text.toString()

                if (name.isBlank()) {
                    inputCategoryName.error = getString(R.string.input_category_name)
                    inputCategoryName.requestFocus()
                    return@setOnClickListener
                }

                if (image.isBlank()) {
                    inputCategoryImage.error = getString(R.string.input_category_image)
                    inputCategoryImage.requestFocus()
                    return@setOnClickListener

                }

                inputCategoryName.error = null
                inputCategoryImage.error = null

                if (category == null) {
                    viewModel.addCategory(
                        name = name,
                        image = image,
                        backgroundColor = R.color.bg_fruits,
                        borderColor = R.color.border_fruits
                    )

                }
                toast(getString(R.string.category_added_list))
                activity?.finish()
            }
        }

    }
}
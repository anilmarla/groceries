package com.anil.groceries.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.anil.groceries.R
import com.anil.groceries.databinding.FragmentExploreBinding
import com.anil.groceries.model.Category
import com.anil.groceries.ui.base.BaseFragment
import com.anil.groceries.utils.MarginItemDecoration

class ExploreFragment : BaseFragment() {
    private lateinit var binding: FragmentExploreBinding
    private lateinit var adapter: CategoriesAdapter

    companion object {
        fun newInstance() = ExploreFragment()
    }

    private lateinit var viewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        renderList()



        return binding.root
    }

    private fun renderList() {
        adapter = CategoriesAdapter()
        binding.recyclerView.adapter = adapter

        adapter.submitList(getCategories())

        // grid layout
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        // space b/w items
        binding.recyclerView.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_small), 2)
        )
    }

    private fun getCategories(): List<Category>? {
        val items = mutableListOf<Category>()

        items.add(Category(getString(R.string.fruits_vegetables), "https://www.pngmart.com/files/17/Organic-Fruits-And-Vegetables-PNG-Image.png", R.color.bg_fruits, borderColor = R.color.border_fruits))
        items.add(Category(getString(R.string.fruits_vegetables), "https://www.pngmart.com/files/17/Organic-Fruits-And-Vegetables-PNG-Image.png", R.color.bg_fruits, borderColor = R.color.border_fruits))
        items.add(Category(getString(R.string.fruits_vegetables), "https://www.pngmart.com/files/17/Organic-Fruits-And-Vegetables-PNG-Image.png", R.color.bg_fruits, borderColor = R.color.border_fruits))
        items.add(Category(getString(R.string.fruits_vegetables), "https://www.pngmart.com/files/17/Organic-Fruits-And-Vegetables-PNG-Image.png", R.color.bg_fruits, borderColor = R.color.border_fruits))
        items.add(Category(getString(R.string.fruits_vegetables), "https://www.pngmart.com/files/17/Organic-Fruits-And-Vegetables-PNG-Image.png", R.color.bg_fruits, borderColor = R.color.border_fruits))
        items.add(Category(getString(R.string.fruits_vegetables), "https://www.pngmart.com/files/17/Organic-Fruits-And-Vegetables-PNG-Image.png", R.color.bg_fruits, borderColor = R.color.border_fruits))
        items.add(Category(getString(R.string.fruits_vegetables), "https://www.pngmart.com/files/17/Organic-Fruits-And-Vegetables-PNG-Image.png", R.color.bg_fruits, borderColor = R.color.border_fruits))
        items.add(Category(getString(R.string.fruits_vegetables), "https://www.pngmart.com/files/17/Organic-Fruits-And-Vegetables-PNG-Image.png", R.color.bg_fruits, borderColor = R.color.border_fruits))
        items.add(Category(getString(R.string.fruits_vegetables), "https://www.pngmart.com/files/17/Organic-Fruits-And-Vegetables-PNG-Image.png", R.color.bg_fruits, borderColor = R.color.border_fruits))
        items.add(Category(getString(R.string.fruits_vegetables), "https://www.pngmart.com/files/17/Organic-Fruits-And-Vegetables-PNG-Image.png", R.color.bg_fruits, borderColor = R.color.border_fruits))
        items.add(Category(getString(R.string.fruits_vegetables), "https://www.pngmart.com/files/17/Organic-Fruits-And-Vegetables-PNG-Image.png", R.color.bg_fruits, borderColor = R.color.border_fruits))
        items.add(Category(getString(R.string.fruits_vegetables), "https://www.pngmart.com/files/17/Organic-Fruits-And-Vegetables-PNG-Image.png", R.color.bg_fruits, borderColor = R.color.border_fruits))
        return items


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ExploreViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
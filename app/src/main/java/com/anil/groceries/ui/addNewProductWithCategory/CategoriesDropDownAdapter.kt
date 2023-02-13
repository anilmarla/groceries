package com.anil.groceries.ui.addNewProductWithCategory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.anil.groceries.model.Category

class CategoriesDropDownAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    private val values: List<Category>
) : ArrayAdapter<Category>(context, layoutResource, values) {

    override fun getItem(position: Int): Category = values[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val category = getItem(position)

        val v = LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false) as TextView

        v.text = category.name
        return v
    }
}
package com.anil.groceries.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.anil.groceries.databinding.CheckoutInfoItemBinding

class CheckoutInfoItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var binding: CheckoutInfoItemBinding
    private var title: String? = null
    private var value: String? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = CheckoutInfoItemBinding.inflate(inflater, this, true)
    }

    fun setData(title: String?, value: String?) {
        this.title = title
        this.value = value

        renderView()
    }

    private fun renderView() {
        binding.title.text = title
        binding.value.text = value
    }
}
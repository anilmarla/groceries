package com.anil.groceries.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anil.groceries.R
import com.anil.groceries.databinding.FragmentCheckoutBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CheckoutBottomSheetFragment(private val listener: CheckoutBottomSheetFragmentListener) :
    BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCheckoutBottomSheetBinding

    override fun getTheme(): Int {
        return R.style.BottomSheetDialogRoundedCorners
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.item1.setData("Delivery", "Select Method")
        binding.item2.setData("Payment", "COD")
        binding.item3.setData("promo-code", "pick discount")
        binding.item4.setData("Total Cost", "5000")

        binding.item1.setOnClickListener {
            listener.onItemClicked(0)
        }

        binding.item2.setOnClickListener {
            listener.onItemClicked(1)
        }
        binding.btnPlaceOrder.setOnClickListener{
            listener.onActionButtonClicked()
        }
    }

    interface CheckoutBottomSheetFragmentListener {
        fun onItemClicked(position: Int)
        fun onActionButtonClicked()
    }
}
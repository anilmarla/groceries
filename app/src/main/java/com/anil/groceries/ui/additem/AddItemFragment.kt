package com.anil.groceries.ui.additem

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.anil.groceries.R
import com.anil.groceries.databinding.FragmentAddItemBinding
import com.anil.groceries.ui.base.BaseFragment
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import timber.log.Timber

class AddItemFragment : BaseFragment() {
    private lateinit var binding: FragmentAddItemBinding
    private var categoryId: String? = null
    private var productImageUri: String? = null
    private val openGalleryContract = registerForActivityResult(ActivityResultContracts.GetContent()){ uri->
        uri?.let {
            productImageUri = it.toString()

            Glide.with(binding.root.context).load(uri).centerCrop().into(binding.image)
        }

    }

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

            cardChoooseImage.setOnClickListener{
                requestPermission()
            }
            btnAddProduct.setOnClickListener {
                val name = inputProductName.text.toString()
                val price = inputProductPrice.text.toString()

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
                if (productImageUri.isNullOrBlank()) {
                    toast("Please input product image ")
                    return@setOnClickListener
                }

                binding.inputProductName.error = null
                binding.inputProductPrice.error = null


                //if (product == null) {
                    /*categoryId?.let {
                        viewModel.addProduct(
                            name = name,
                            price = price.toInt(),
                            image = productImageUri.toString(),
                            categoryId = it
                        )
                        toast(getString(R.string.product_added))
                        Timber.e("the product is $name ")
                        Timber.e("price is $price ")
                        activity?.finish()
                    }*/
            }
        }
    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryId = it.getString("category_id")
        }
    }

    private fun requestPermission(){
        Dexter.withContext(context)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                   Timber.e("Read storage permission granted")
                    openGallery()
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    if(p0?.isPermanentlyDenied==true){
                        toast("Permissions denied. Please provide them in the settings")

                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    p1?.continuePermissionRequest()
                }
            }).check()
    }

    private fun openGallery() {
        openGalleryContract.launch("image/*")
    }
}
package com.anil.groceries.ui.add_category

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.anil.groceries.R
import com.anil.groceries.databinding.FragmentAddCategoryBinding
import com.anil.groceries.model.Category
import com.anil.groceries.ui.base.BaseFragment
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import timber.log.Timber

class AddCategoryFragment : BaseFragment() {
    private lateinit var binding: FragmentAddCategoryBinding
    private val category: Category? = null
    private val viewModel: AddCategoryViewModel by viewModels()
    private var categoryImageUri: String? = null

    private val openGalleryContract =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            Timber.e("Gallery image path $uri")

            uri?.let {
                categoryImageUri = it.toString()

                // display category image
                Glide.with(binding.root.context).load(uri).centerCrop().into(binding.image)
            }
        }

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

            cardChoooseImage.setOnClickListener {
                requestPermission()
            }

            btnAddCategory.setOnClickListener {
                val name = inputCategoryName.text.toString()

                if (name.isBlank()) {
                    inputCategoryName.error = getString(R.string.input_category_name)
                    inputCategoryName.requestFocus()
                    return@setOnClickListener
                }

                if (categoryImageUri.isNullOrBlank()) {
                    toast(getString(R.string.error_select_category_image))
                    return@setOnClickListener
                }

                inputCategoryName.error = null

                if (category == null) {
                    viewModel.addCategory(
                        name = name,
                        image = categoryImageUri.toString(),
                        backgroundColor = R.color.bg_fruits,
                        borderColor = R.color.border_fruits
                    )
                }
                toast(getString(R.string.category_added_list))
                activity?.finish()
            }
        }

    }

    /***
     * Requesting Read storage runtime permission
     */
    private fun requestPermission() {
        Dexter.withContext(context)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    Timber.e("Read storage permission granted")
                    openGallery()
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    if (p0?.isPermanentlyDenied == true) {
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
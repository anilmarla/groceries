package com.anil.groceries.ui.account

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.anil.groceries.R
import com.anil.groceries.databinding.FragmentAccountBinding
import com.anil.groceries.model.Account
import com.anil.groceries.model.User
import com.anil.groceries.ui.base.BaseFragment
import com.anil.groceries.ui.login.LoginViewModel
import com.anil.groceries.ui.splash.SplashViewModel
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import timber.log.Timber

class AccountFragment : BaseFragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var adapter: AccountFragmentAdapter
    private val splashViewModel: SplashViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private var user: User? = null

    private val openGalleryContract =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            it?.let { profileImageUri ->
                // store profile picture in db
                user?.let { u ->
                    u.profilePicture = profileImageUri.toString()
                    // update in db
                    loginViewModel.update(u)
                }
            }
        }


    companion object {
        fun newInstance() = AccountFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AccountFragmentAdapter()
        binding.recyclerView.adapter = adapter

        val items = mutableListOf<Account>()

        items.add(Account(R.drawable.ic_orders, getString(R.string.orders)))
        items.add(Account(R.drawable.ic_my_details, getString(R.string.my_details)))
        items.add(Account(R.drawable.ic_delivery_address, getString(R.string.delivert_address)))
        items.add(Account(R.drawable.ic_payment_methods, getString(R.string.payment_methods)))
        items.add(Account(R.drawable.ic_promo_cord, getString(R.string.promo_code)))
        items.add(Account(R.drawable.ic_notification, getString(R.string.notifications)))
        items.add(Account(R.drawable.ic_help_icon, getString(R.string.help)))
        items.add(Account(R.drawable.ic_about_icon, getString(R.string.about)))

        adapter.submitList(items)

        binding.apply {
            profileImage.setOnClickListener {
                requestPermission()
            }
        }

        splashViewModel.getLoggedInUser()

        splashViewModel.loggedInUser.observe(viewLifecycleOwner) { user ->
            user?.let {
                this.user = it

                binding.name.text = it.name
                binding.email.text = it.email

                Timber.e("Profile pic is : ${it.profilePicture}")

                if (it.profilePicture.isNullOrBlank()) {
                    Glide.with(binding.root.context).load(it.profilePicture).circleCrop()
                        .into(binding.profileImage)
                } else {
                    Glide.with(binding.root.context).load(R.drawable.ic_person).circleCrop()
                        .into(binding.profileImage)
                }
            }
        }
    }

    private fun requestPermission() {
        Dexter.withContext(context)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    openGallery()
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    if (p0?.isPermanentlyDenied == true) {
                        toast("Permission denied. Please provide them in the settings")
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
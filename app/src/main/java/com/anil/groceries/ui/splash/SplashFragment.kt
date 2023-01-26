package com.anil.groceries.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anil.groceries.databinding.FragmentSplashBinding
import com.anil.groceries.ui.base.BaseFragment
import com.anil.groceries.ui.home.HomeActivity
import com.anil.groceries.ui.login.LoginActivity

class SplashFragment : BaseFragment() {
    private lateinit var binding: FragmentSplashBinding

    private val viewModel: SplashViewModel by viewModels()

    companion object {
        fun newInstance() = SplashFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLoggedInUser()

        viewModel.loggedInUser.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                Handler(Looper.getMainLooper()).postDelayed({
                    launchLogin()
                }, 2000)
                launchLogin()
            } else {
                Handler(Looper.getMainLooper()).postDelayed({
                    launchHome()
                }, 2000)
            }
        }
    }
    private fun launchLogin() {
        val intent = Intent(activity, LoginActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        activity?.finish()
    }

    private fun launchHome() {
        val intent = Intent(activity, HomeActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        activity?.finish()
    }


}
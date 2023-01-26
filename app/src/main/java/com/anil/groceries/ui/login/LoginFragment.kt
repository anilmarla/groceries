package com.anil.groceries.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.anil.groceries.R
import com.anil.groceries.databinding.FragmentLoginBinding
import com.anil.groceries.ui.base.BaseFragment
import com.anil.groceries.ui.home.HomeActivity
import com.anil.groceries.ui.register.RegisterActivity
import timber.log.Timber

class LoginFragment : BaseFragment() {
    private lateinit var binding: FragmentLoginBinding

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonLogin.setOnClickListener {
                if (validateLoginForm()) {
                    viewModel.getUserByEmailAndPassword(
                        binding.inputEmail.text.toString(),
                        binding.inputPassword.text.toString()
                    )
                }
            }
            btnSignUp.setOnClickListener {
                launchRegister()
            }

        }
        viewModel.user.observe(viewLifecycleOwner) {
            Timber.e("logged in User $it")

            if (it != null) {
                launchHome()
            } else {
                toast("Invalid credentials. Please try again!")
            }
        }
    }

    private fun launchHome() {
        val intent = Intent(activity, HomeActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
    private fun launchRegister() {
        val intent = Intent(activity, RegisterActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun validateLoginForm(): Boolean {
        binding.apply {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            if (email.isBlank()) {
                inputEmail.error = getString(R.string.enter_email_error)
                inputEmail.requestFocus()
                return false
            } else {
                inputEmail.error = null
            }
            if (password.isBlank()) {
                inputPassword.error = getString(R.string.enter_password_error)
                inputPassword.requestFocus()
                return false
            } else {
                inputPassword.error = null
            }
        }
        return true

    }
}



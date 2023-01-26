package com.anil.groceries.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.anil.groceries.R
import com.anil.groceries.databinding.FragmentRegisterBinding
import com.anil.groceries.ui.base.BaseFragment
import com.anil.groceries.ui.login.LoginActivity
import timber.log.Timber

class RegisterFragment : BaseFragment() {
    private lateinit var binding: FragmentRegisterBinding

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private val viewModel: RegisterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnSignup.setOnClickListener {
                if (validateForm()) {
                    val email = inputEmail.text.toString()
                    viewModel.getUserByEmailId(email)
                }
            }

            btnSignIn.setOnClickListener {
                launchLogin()
            }
        }

        viewModel.user.observe(viewLifecycleOwner) { user ->
            Timber.e("User from db $user")

            if (user != null) {
                toast("User with email ID already registered!")
            } else {
                registerUser()
            }
        }

        viewModel.users.observe(viewLifecycleOwner) { users ->
            Timber.e("Users $users")
        }
    }

    private fun registerUser() {
        binding.apply {
            val name = inputName.text.toString()
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            viewModel.addUser(name = name, email = email, password = password)

            toast(getString(R.string.register_sucessful_dailouge))

            launchLogin()
        }
    }

    private fun launchLogin() {
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun validateForm(): Boolean {
        binding.apply {
            val name = inputName.text.toString()
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            if (name.isBlank()) {
                inputName.error = getString(R.string.enter_name_error)
                inputName.requestFocus()
                return false
            } else {
                inputName.error = null
            }
            if (email.isBlank()) {
                inputEmail.error = getString(R.string.input_email_error)
                inputEmail.requestFocus()
                return false

            } else {
                inputEmail.error = null
            }
            if (password.isBlank()) {
                inputPassword.error = getString(R.string.input_password_error)
                inputPassword.requestFocus()
                return false
            } else {
                inputPassword.error = null
            }
            return true
        }
    }
}
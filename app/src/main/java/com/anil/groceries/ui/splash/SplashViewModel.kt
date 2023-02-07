package com.anil.groceries.ui.splash

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anil.groceries.model.User
import com.anil.groceries.repository.UsersRepository
import com.anil.groceries.ui.base.BaseViewModel

class SplashViewModel(application: Application) : BaseViewModel(application) {
    private var usersRepository: UsersRepository
    var loggedInUser: LiveData<User> = MutableLiveData()

    init {
        usersRepository = UsersRepository(application)
        loggedInUser = usersRepository.isLoggedInUser()
    }
}
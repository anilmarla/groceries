package com.anil.groceries.ui.splash

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anil.groceries.model.User
import com.anil.groceries.repository.UsersRepository
import com.anil.groceries.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : BaseViewModel(application) {
    private var usersRepository: UsersRepository
    var loggedInUser: MutableLiveData<User> = MutableLiveData()


    init {
        usersRepository = UsersRepository(application)
    }

    fun getLoggedInUser() {
        viewModelScope.launch(Dispatchers.IO) {
            loggedInUser.postValue(usersRepository.isLoggedInUser())
        }
    }



}
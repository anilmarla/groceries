package com.anil.groceries.ui.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anil.groceries.model.User
import com.anil.groceries.repository.UsersRepository
import com.anil.groceries.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : BaseViewModel(application) {
    private var usersRepository: UsersRepository
    var user: MutableLiveData<User> = MutableLiveData()


    init {
        usersRepository = UsersRepository(application)
    }

    fun getUserByEmailAndPassword(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            user.postValue(usersRepository.getUserByEmailAndPassword(email, password))
        }
    }

}


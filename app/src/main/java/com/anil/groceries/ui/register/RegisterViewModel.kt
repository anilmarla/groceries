package com.anil.groceries.ui.register

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anil.groceries.model.User
import com.anil.groceries.repository.UsersRepository
import com.anil.groceries.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class RegisterViewModel(application: Application) : BaseViewModel(application) {
    private var usersRepository: UsersRepository
    var users: LiveData<List<User>> = MutableLiveData()
    var user: MutableLiveData<User> = MutableLiveData()

    init {
        usersRepository = UsersRepository(application)
        users = usersRepository.getUsers()
    }

    fun addUser(name: String, email: String, password: String) {
        val user = User(
            id = UUID.randomUUID().toString(),
            name = name,
            email = email,
            password = password
        )
        viewModelScope.launch(Dispatchers.IO) {
            usersRepository.insert(user)
        }
    }

    fun getUserByEmailId(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            user.postValue(usersRepository.getUserByEmailId(email))
        }
    }
}



package com.anil.groceries.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anil.groceries.database.AppDatabase
import com.anil.groceries.model.User
import com.anil.groceries.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private var usersRepository: UsersRepository
    var users: MutableLiveData<List<User>> = MutableLiveData()

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()

        usersRepository = UsersRepository(userDao)

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

}

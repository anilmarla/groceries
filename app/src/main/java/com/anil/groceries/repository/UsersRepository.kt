package com.anil.groceries.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.anil.groceries.database.AppDatabase
import com.anil.groceries.database.dao.UserDao
import com.anil.groceries.model.User

class UsersRepository(application: Application) {
    private var userDao: UserDao

    init {
        userDao = AppDatabase.getDatabase(application).userDao()
    }

    fun getUsers(): LiveData<List<User>> {
        return userDao.getAll()
    }

    fun insert(user: User) {
        return userDao.insert(user)
    }

    fun update(user: User) {
        return userDao.update(user)
    }

    fun getUserByEmailId(email: String): User {
        return userDao.getUserEmailId(email)
    }

    fun getUserByEmailAndPassword(email: String, password: String): User {
        return userDao.getUserEmailAndPassword(email, password)
    }

    fun isLoggedInUser(): LiveData<User> {
        return userDao.isLoggedInUser()
    }
}
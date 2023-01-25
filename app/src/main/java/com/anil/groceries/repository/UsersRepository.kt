package com.anil.groceries.repository

import androidx.lifecycle.LiveData
import com.anil.groceries.database.dao.UserDao
import com.anil.groceries.model.User

class UsersRepository(private val userDao: UserDao) {



    fun getUsers(): LiveData<List<User>> {
        return userDao.getAll()
    }

    fun insert(user: User) {
        return userDao.insert(user)
    }

    fun update(user: User) {
        return userDao.update(user)
    }
}
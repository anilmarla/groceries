package com.anil.groceries.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.anil.groceries.model.User


@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * from users")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * from users WHERE email=:email")
    fun getUserEmailId(email: String): User

    @Query("SELECT * from users WHERE isLoggedIn=true")
    fun isLoggedInUser(): User

}
package com.anil.groceries.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anil.groceries.database.dao.CategoryDao
import com.anil.groceries.database.dao.ProductDao
import com.anil.groceries.database.dao.UserDao
import com.anil.groceries.model.Category
import com.anil.groceries.model.Product
import com.anil.groceries.model.User
import com.anil.groceries.utils.Converts


@Database(
    entities = [User::class, Product::class, Category::class], version = 8
)
@TypeConverters(Converts::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        // Singleton prevents multiple
        // instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "login_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
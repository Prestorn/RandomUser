package com.example.randomuser.data.storage

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.randomuser.data.storage.dao.UserDao
import com.example.randomuser.data.storage.models.UserInStorage

@Database(
    entities = [UserInStorage::class],
    version = 2
)
abstract class AppDB : RoomDatabase(){
    abstract val userDao: UserDao
}

fun provideAppDB(app: Application): AppDB {
    return Room.databaseBuilder(
        context = app,
        klass = AppDB::class.java,
        name = "app_database"
    ).fallbackToDestructiveMigration(true).build()
}
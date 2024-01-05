package com.example.news24.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShortenedDoc::class], version = 1)
abstract class MainDatabase: RoomDatabase() {

    abstract fun getDao(): Dao

    companion object{
        @Volatile
        private var instance: MainDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context): MainDatabase {
            return Room.databaseBuilder(context.applicationContext, MainDatabase::class.java,"news.db").allowMainThreadQueries().build()
        }
    }
}
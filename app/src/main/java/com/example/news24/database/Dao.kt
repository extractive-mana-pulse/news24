package com.example.news24.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNews(shd: ShortenedDoc)

    @Query("SELECT * FROM News")
    fun getAllNews():LiveData<List<ShortenedDoc>>

    @Query("SELECT * FROM News ORDER BY id ASC")
    fun readAllData(): LiveData<List<ShortenedDoc>>

    @Delete
    fun delete(shd: ShortenedDoc)

    @Query("SELECT * FROM News WHERE id = :itemId OR title = :itemTitle")
    fun getItemByIdOrTitle(itemId: Int, itemTitle: String): ShortenedDoc?

    @Query("SELECT * FROM News WHERE id = :id")
    fun getEntityById(id: Int?): ShortenedDoc?
}
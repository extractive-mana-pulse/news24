package com.example.news24.mvvm

import androidx.lifecycle.LiveData
import com.example.news24.database.Dao
import com.example.news24.database.ShortenedDoc
import javax.inject.Inject

class NewsRepository @Inject constructor(private val dao: Dao) {

    val readAllData: LiveData<List<ShortenedDoc>> = dao.readAllData()

    suspend fun deleteNote(shortenedDoc: ShortenedDoc){
        dao.delete(shortenedDoc)
    }

    // Function to check if data with a specific ID exists
    fun isDataExistById(id: Int?): Boolean {
        val entity = dao.getEntityById(id)
        return entity != null
    }
}
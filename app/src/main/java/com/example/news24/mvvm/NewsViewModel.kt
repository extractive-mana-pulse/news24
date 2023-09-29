package com.example.news24.mvvm

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.news24.database.MainDatabase
import com.example.news24.database.ShortenedDoc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor(application: Application): AndroidViewModel(application) {

    val readAllData : LiveData<List<ShortenedDoc>>

    val someTestData = MutableLiveData<List<ShortenedDoc>>()

    private val repository : NewsRepository

    init {
        val dao = MainDatabase.invoke(application).getDao()
        repository = NewsRepository(dao)
        readAllData = repository.readAllData
    }

    fun deleteNote(shortenedDoc: ShortenedDoc){
        viewModelScope.launch ( Dispatchers.IO){
            repository.deleteNote(shortenedDoc)
        }
    }
}
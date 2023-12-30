package com.example.news24.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news24.data.search.Head
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<Head>> = MutableLiveData()

    val searchNews: MutableLiveData<Resource<Head>> = MutableLiveData()


    fun getNewsAutomobile() {
        viewModelScope.launch {
            val response = repository.getNewsAutomobile()
            myResponse.value = response
        }
    }
    fun getNewsBusiness() {
        viewModelScope.launch {
            val response = repository.getNewsBusiness()
            myResponse.value = response
        }
    }
    fun getNewsSport() {
        viewModelScope.launch {
            val response = repository.getNewsSports()
            myResponse.value = response
        }
    }
    fun getNewsWorld() {
        viewModelScope.launch {
            val response = repository.getNewsWorld()
            myResponse.value = response
        }
    }
    fun getNewsScience() {
        viewModelScope.launch {
            val response = repository.getNewsScience()
            myResponse.value = response
        }
    }
    fun getNewsNational() {
        viewModelScope.launch {
            val response = repository.getNewsNational()
            myResponse.value = response
        }
    }

    fun getNewsTechnology() {
        viewModelScope.launch {
            val response = repository.getNewsTechnology()
            myResponse.value = response
        }
    }

    fun getNewsPolitics() {
        viewModelScope.launch {
            val response = repository.getNewsPolitics()
            myResponse.value = response
        }
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = repository.searchNews(searchQuery)
        searchNews.postValue(handleSearchNews(response))
    }

    private fun handleSearchNews(response: Response<Head>): Resource<Head>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
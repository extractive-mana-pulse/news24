package com.example.news24.viewModel

import com.example.news24.data.api.RetrofitInstance
import com.example.news24.data.search.Head
import retrofit2.Response

class Repository {


    suspend fun getNewsAutomobile() : Response<Head> {
        return RetrofitInstance.api.getNewsAutomobile("Automobiles",0)
    }
    suspend fun getNewsBusiness() : Response<Head> {
        return RetrofitInstance.api.getNewsBusiness("Business",1)
    }
    suspend fun getNewsSports() : Response<Head> {
        return RetrofitInstance.api.getNewsSports("Sports",2)
    }
    suspend fun getNewsWorld() : Response<Head> {
        return RetrofitInstance.api.getNewsWorld("World",3)
    }
    suspend fun getNewsScience() : Response<Head> {
        return RetrofitInstance.api.getNewsScience("Science",4)
    }
    suspend fun getNewsNational() : Response<Head> {
        return RetrofitInstance.api.getNewsNational("National",5)
    }
    suspend fun getNewsTechnology() : Response<Head> {
        return RetrofitInstance.api.getNewsTechnology("Technology",6)
    }
    suspend fun getNewsPolitics() : Response<Head> {
        return RetrofitInstance.api.getNewsPolitics("Politics",7)
    }
    suspend fun searchNews(searchQuery: String) = RetrofitInstance.api.searchNews(searchQuery)
}
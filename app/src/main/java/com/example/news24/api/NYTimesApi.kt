package com.example.news24.api

import com.example.news24.constants.Constants.Companion.API_KEY
import com.example.news24.data.search.Head
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTimesApi {

    @GET("svc/search/v2/articlesearch.json?q=automobile&api-key=${API_KEY}")
    suspend fun getNewsAutomobile(
        @Query("q")
        q : String = "Automobiles",
        @Query("page")
        pageNumber: Int = 0
    ):Response<Head>

    @GET("svc/search/v2/articlesearch.json?q=business&api-key=${API_KEY}")
    suspend fun getNewsBusiness(
        @Query("q")
        q : String = "Business",
        @Query("page")
        pageNumber: Int = 1
    ):Response<Head>

    @GET("svc/search/v2/articlesearch.json?q=sports&api-key=${API_KEY}")
    suspend fun getNewsSports(
        @Query("q")
        q : String = "Sports",
        @Query("page")
        pageNumber: Int = 2
    ):Response<Head>

    @GET("svc/search/v2/articlesearch.json?q=world&api-key=${API_KEY}")
    suspend fun getNewsWorld(
        @Query("q")
        q : String = "World",
        @Query("page")
        pageNumber: Int = 3
    ):Response<Head>

    @GET("svc/search/v2/articlesearch.json?q=science&api-key=${API_KEY}")
    suspend fun getNewsScience(
        @Query("q")
        q : String = "Science",
        @Query("page")
        pageNumber: Int = 4
    ):Response<Head>

    @GET("svc/search/v2/articlesearch.json?q=national&api-key=${API_KEY}")
    suspend fun getNewsNational(
        @Query("q")
        q : String = "National",
        @Query("page")
        pageNumber: Int = 5
    ):Response<Head>

    @GET("svc/search/v2/articlesearch.json?q=technology&api-key=${API_KEY}")
    suspend fun getNewsTechnology(
        @Query("q")
        q : String = "Technology",
        @Query("page")
        pageNumber: Int = 6
    ):Response<Head>

    @GET("svc/search/v2/articlesearch.json?q=politics&api-key=${API_KEY}")
    suspend fun getNewsPolitics(
        @Query("q")
        q : String = "Politics",
        @Query("page")
        pageNumber: Int = 7
    ):Response<Head>

    @GET("svc/search/v2/articlesearch.json?q=all&api-key=${API_KEY}")
    suspend fun searchNews(@Query("q") q : String = "all", ):Response<Head>
}
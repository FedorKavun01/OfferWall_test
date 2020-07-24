package com.example.offerwall_test

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("trending")
    fun getTrends(): Deferred<Response<List<Item>>>

    @GET("object/{id}")
    fun getNote(@Path("id")id: Int): Deferred<Response<Note>>

    @GET("object/{id}")
    fun getTextNote(@Path("id")id: Int): Deferred<Response<TextNote>>

    @GET("object/{id}")
    fun getWebNote(@Path("id")id: Int): Deferred<Response<WebNote>>
}

object NetworkService {
    private val baseUrl = "https://demo0040494.mockable.io/api/v1/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun getService(): APIService {
        return retrofit.create(APIService::class.java)
    }
}
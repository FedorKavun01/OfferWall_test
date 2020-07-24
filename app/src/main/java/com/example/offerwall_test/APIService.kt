package com.example.offerwall_test

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("trending")
    fun getTrends(): Call<List<Item>>

    @GET("object/{id}")
    fun getNote(@Path("id")id: Int): Call<Note>

    @GET("object/{id}")
    fun getTextNote(@Path("id")id: Int): Call<TextNote>

    @GET("object/{id}")
    fun getWebNote(@Path("id")id: Int): Call<WebNote>
}

object NetworkService {
    private val baseUrl = "https://demo0040494.mockable.io/api/v1/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun getService(): APIService {
        return retrofit.create(APIService::class.java)
    }
}
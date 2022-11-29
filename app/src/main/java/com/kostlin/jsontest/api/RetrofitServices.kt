package com.example.jsontest.api

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface RetrofitServices {

    @GET("test.php")
    fun getPosts(): Call<MutableList<PostModel>>

    @GET("test.php")
    fun getPostsDetail(@Query("id")id: Int): Call<MutableList<PostModel>>
}
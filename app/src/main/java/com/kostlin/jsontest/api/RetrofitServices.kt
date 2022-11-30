package com.example.jsontest.api

import com.example.jsontest.ui.main.DetailFragment
import com.kostlin.jsontest.api.PostModelDetail
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface RetrofitServices {

    @GET("test.php")
    fun getPosts(): Call<MutableList<PostModel>>

    @GET("test.php")
    fun getPostsDetail(@Query("id") id: Int): Call<MutableList<PostModelDetail>>

}
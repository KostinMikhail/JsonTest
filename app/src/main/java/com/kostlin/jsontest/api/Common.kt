package com.kostlin.jsontest.api


import com.example.jsontest.api.RetrofitServices



object Common {
    private val BASE_URL = "https://lifehack.studio/test_task/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}




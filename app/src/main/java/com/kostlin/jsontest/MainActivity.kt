package com.example.jsontest


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jsontest.api.PostAdapter
import com.kostlin.jsontest.api.Common
import com.example.jsontest.api.RetrofitServices
import com.example.jsontest.api.PostModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var mService: RetrofitServices
    var adapter = PostAdapter().apply {
        onClick = { position ->
            Toast.makeText(baseContext, "Нажат номер :$position", Toast.LENGTH_SHORT).show()

            supportFragmentManager.beginTransaction()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.kostlin.jsontest.R.layout.activity_main)

        mService = Common.retrofitService
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        getAllCompaines()


    }

    private fun getAllCompaines() {

        mService.getPosts().enqueue(object : Callback<MutableList<PostModel>> {
            override fun onFailure(call: Call<MutableList<PostModel>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<MutableList<PostModel>>,
                response: Response<MutableList<PostModel>>
            ) {
                response.body()?.let {
                    adapter.postList = it
                }
                adapter.notifyDataSetChanged()
                myRecyclerView.adapter = adapter


            }
        })

    }
}
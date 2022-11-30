package com.example.jsontest.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.jsontest.api.RetrofitServices
import com.kostlin.jsontest.R
import com.kostlin.jsontest.api.Common
import com.kostlin.jsontest.api.PostModelDetail
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post_model_layout.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class DetailFragment : Fragment() {

    lateinit var mService: RetrofitServices

    companion object {
        fun newInstance(position: Int): DetailFragment {
            val args = Bundle()
            args.putInt("position", position)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }

    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.post_model_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mService = Common.retrofitService
        getDetails()
        tvId.text = arguments?.getInt("position").toString()
        //tvName.text = arguments?.getString("name").toString()


    }


    private fun getDetails() {

        mService.getPostsDetail(arguments?.getInt("position")!!).enqueue(object : Callback,
            retrofit2.Callback<MutableList<PostModelDetail>> {
            override fun onResponse(
                call: Call<MutableList<PostModelDetail>>,
                response: Response<MutableList<PostModelDetail>>
            ) {
                println(response.code())
                response.body()?.let {
                    tvId.text = it.id.toString()
                    tvName.text = it.


                }

            }

            override fun onFailure(call: Call<MutableList<PostModelDetail>>, t: Throwable) {
                Log.e("DetailFragmentONFailure", "error")
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

}
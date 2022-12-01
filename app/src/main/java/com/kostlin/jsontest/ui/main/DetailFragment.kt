package com.example.jsontest.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.jsontest.api.RetrofitServices
import com.kostlin.jsontest.R
import com.kostlin.jsontest.api.Common
import com.kostlin.jsontest.api.PostModelDetail
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
    }

    fun place(postModelDetail: MutableList<PostModelDetail>): String {
        val allDetails = mutableListOf(postModelDetail)
        val modelPostModelDetail =
            allDetails.elementAt(0)

        val name: String = modelPostModelDetail.firstOrNull()?.name.toString()
        tvId.text = modelPostModelDetail.firstOrNull()?.id.toString()
        tvName.text = modelPostModelDetail.firstOrNull()?.name.toString()
        tvDescription.text = modelPostModelDetail.firstOrNull()?.description.toString()
        tvLat.text = modelPostModelDetail.firstOrNull()?.lat.toString()
        tvLon.text = modelPostModelDetail.firstOrNull()?.lon.toString()
        tvWww.text = modelPostModelDetail.firstOrNull()?.www.toString()
        tvPhone.text = modelPostModelDetail.firstOrNull()?.phone.toString()
        Glide.with(ivImg.context)
            .load("https://lifehack.studio/test_task/${modelPostModelDetail.firstOrNull()?.img}")
            .placeholder(R.drawable.ic_launcher_background)
            .into(ivImg)
        return name
    }

    private fun getDetails() {

        mService.getPostsDetail(arguments?.getInt("position")!!).enqueue(object : Callback,
            retrofit2.Callback<MutableList<PostModelDetail>> {
            override fun onResponse(
                call: Call<MutableList<PostModelDetail>>,
                response: Response<MutableList<PostModelDetail>>
            ) {
                response.body()?.let {
                    place(it)
                }
            }

            override fun onFailure(call: Call<MutableList<PostModelDetail>>, t: Throwable) {
                Log.e("DetailFragmentOnFailure", "error")
            }
        })
    }
}
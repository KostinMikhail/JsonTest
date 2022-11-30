package com.example.jsontest.api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kostlin.jsontest.R
import com.kostlin.jsontest.api.PostModelDetail
import com.squareup.picasso.Picasso



class PostAdapter() :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var onClick: (position: Int) -> Unit = {}
    var postList: MutableList<PostModel> = mutableListOf()
    var postListDetail: MutableList<PostModelDetail> = mutableListOf()

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvID: TextView = itemView.findViewById(R.id.tvID)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val ivIMG: ImageView = itemView.findViewById(R.id.image)
        val container: CardView = itemView.findViewById(R.id.container)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_post, parent, false)
        return PostViewHolder(itemView)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val listItem = postList[position]
        Glide.with(holder.ivIMG.context)
            .load("https://lifehack.studio/test_task/${listItem.img}")
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.ivIMG)

        holder.tvID.text = listItem.id.toString()
        holder.tvName.text = listItem.name
        holder.container.setOnClickListener{
            onClick.invoke(listItem.id?:0)
        }
    }

}


package com.example.digitalrepoproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.digitalrepoproject.Models.RepoModel
import com.example.digitalrepoproject.R

class ReposAdapter (val context:Context,val repoList:ArrayList<RepoModel>)
    : RecyclerView.Adapter<ReposAdapter.myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.repo_item_layout,parent,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val repoModel:RepoModel = repoList.get(position)

            Glide.with(context)
                .load(repoModel.getOwner()?.getAvatarUrl())
                .into(holder.ownerImage)

            holder.repoNameTv.setText(repoModel.getFullName())
            holder.repoUrlTv.setText(repoModel.getHtmlUrl())

    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repoNameTv =itemView.findViewById<TextView>(R.id.repoName)
        val ownerImage = itemView.findViewById<ImageView>(R.id.img)
        val repoUrlTv = itemView.findViewById<TextView>(R.id.repoUrl)
    }

    fun addMoreData(list:List<RepoModel>){
        repoList.addAll(list)
        notifyDataSetChanged()
    }
}

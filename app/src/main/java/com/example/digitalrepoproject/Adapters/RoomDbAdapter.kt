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
import com.example.digitalrepoproject.RoomArchitecture.RepoEntity

class RoomDbAdapter(val context: Context) : RecyclerView.Adapter<RoomDbAdapter.myViewHolder>() {

    var repoSavedList = emptyList<RepoEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.repo_item_layout,parent,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repoSavedList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val repoEntity = repoSavedList.get(position)

        Glide.with(context)
            .load(repoEntity.imageUrl)
            .into(holder.ownerImage)

        holder.repoNameTv.setText(repoEntity.fullName)
        holder.repoUrlTv.setText(repoEntity.imageUrl)
    }

    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repoNameTv =itemView.findViewById<TextView>(R.id.repoName)
        val ownerImage = itemView.findViewById<ImageView>(R.id.img)
        val repoUrlTv = itemView.findViewById<TextView>(R.id.repoUrl)
    }

    fun setData(repoEntity: List<RepoEntity>){
        this.repoSavedList=repoEntity
        notifyDataSetChanged()
    }


}
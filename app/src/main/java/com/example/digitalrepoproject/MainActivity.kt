package com.example.digitalrepoproject

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife

import com.example.digitalrepoproject.Adapters.ReposAdapter
import com.example.digitalrepoproject.Models.RepoModel
import com.example.digitalrepoproject.RoomArchitecture.JackRoomViewModel
import com.example.digitalrepoproject.RoomArchitecture.RepoEntity
import com.example.digitalrepoproject.Utils.AppUtiles
import com.example.digitalrepoproject.ViewModels.RepoViewModel


class MainActivity : AppCompatActivity() {

    lateinit var progressBar :ProgressBar
    var emptyList = ArrayList<RepoModel>()
    var adapter = ReposAdapter(this,emptyList)
    val reposSavedList = emptyList<RepoEntity>()




    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val  recyclerView = findViewById<RecyclerView>(R.id.myRec)
       progressBar=findViewById(R.id.progressBar)


        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val appUtiles = AppUtiles()



        if(appUtiles.isOnline(this)) {
            val repoViewModel =  ViewModelProvider(this).get(RepoViewModel::class.java)
            //Observe on showing progress bar or hide it
            repoViewModel.dataRepository.myFlag.observe(this, Observer {
                t ->
                if(t) hideProgressBar()
                else showProgressBar()
            })

            //Observe on initial Call
            repoViewModel.getRepoModel()
                .observe(this, Observer<ArrayList<RepoModel>> { repoList: ArrayList<RepoModel> ->

                     adapter = ReposAdapter(this, repoList)
                    recyclerView.adapter = adapter

                    hideProgressBar()
                })

            //Observe on Paging call
            repoViewModel.getPagingData().observe(this, Observer<ArrayList<RepoModel>> {
                repoList ->
                adapter.addMoreData(repoList)
                hideProgressBar()
            })

            //Recyclerview Scroll listener
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val total = adapter.itemCount
                       if(dy>0) {
                           if ((visibleItemCount + pastVisibleItem) > total){
                                repoViewModel.dataRepository.myFlag.value=true
                                repoViewModel.getPagingData()
                               repoViewModel.dataRepository.myFlag.value=false
                            }
                       }
                }
            })
        }
        else {



            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show()
        }

    }

     fun showProgressBar(){
        progressBar.visibility=View.VISIBLE
    }

    fun hideProgressBar(){
        progressBar.visibility=View.GONE
    }
}

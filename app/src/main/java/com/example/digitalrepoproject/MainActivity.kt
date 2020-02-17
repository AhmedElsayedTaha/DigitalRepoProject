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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife

import com.example.digitalrepoproject.Adapters.ReposAdapter
import com.example.digitalrepoproject.Models.RepoModel
import com.example.digitalrepoproject.Utils.AppUtiles
import com.example.digitalrepoproject.ViewModels.RepoViewModel


class MainActivity : AppCompatActivity() {


    /*@BindView(R.id.myRec)
    lateinit var recyclerView :RecyclerView*/
    lateinit var progressBar :ProgressBar
    lateinit var adapter : ReposAdapter
    var total =0



    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       val  recyclerView = findViewById<RecyclerView>(R.id.myRec)
       progressBar=findViewById(R.id.progressBar)


        recyclerView.layoutManager = LinearLayoutManager(this)
        val appUtiles = AppUtiles()

        if(appUtiles.isOnline(this)) {
            showProgressBar()
            val repoViewModel = RepoViewModel()

            //Observe on showing progress bar or hide it
            repoViewModel.dataRepository.myFlag.observe(this, Observer {
                t ->
                if(t){
                    hideProgressBar()
                }
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

            })

            //Recyclerview Scroll listener
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val visibleItemCount = LinearLayoutManager(this@MainActivity).childCount
                    val pastVisibleItem =
                        LinearLayoutManager(this@MainActivity).findFirstCompletelyVisibleItemPosition()
                    total = adapter.itemCount

                        if(dy>0) {
                            if ((visibleItemCount + pastVisibleItem) == total-1) {
                                repoViewModel.dataRepository.myFlag.value=false
                                repoViewModel.getPagingData()
                            }
                        }


                    super.onScrolled(recyclerView, dx, dy)
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

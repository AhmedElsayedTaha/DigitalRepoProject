package com.example.digitalrepoproject.Repositories

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.digitalrepoproject.Models.RepoModel
import com.example.digitalrepoproject.Network.APIClient
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DataRepository {
    val dataMutableLive = MutableLiveData<ArrayList<RepoModel>>()
    val pagedListMutableLiveData = MutableLiveData<ArrayList<RepoModel>>()
    var FIRST_PAGE =1;
    val PAGE_COUNT =15;
    val apiClient =APIClient()
    var myFlag = MutableLiveData<Boolean>()




    @SuppressLint("CheckResult")
    fun getData(): MutableLiveData<ArrayList<RepoModel>> {

        apiClient.getRetrofitInstance().getRepo(FIRST_PAGE,PAGE_COUNT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object :DisposableSingleObserver<ArrayList<RepoModel>>() {
                override fun onSuccess(list: ArrayList<RepoModel>) {
                    dataMutableLive.value=list

                }

                override fun onError(e: Throwable) {
                    Log.e("error",""+e.message)
                }

            })

        return dataMutableLive
    }

    @SuppressLint("CheckResult")
    fun performPagination():MutableLiveData<ArrayList<RepoModel>>{
        myFlag.value=true
        FIRST_PAGE++
        Log.e("page",""+FIRST_PAGE)
        apiClient.getRetrofitInstance().getRepo(FIRST_PAGE,PAGE_COUNT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object :DisposableSingleObserver<ArrayList<RepoModel>>() {
                override fun onSuccess(list: ArrayList<RepoModel>) {
                    pagedListMutableLiveData.value=list
                }

                override fun onError(e: Throwable) {
                    Log.e("error",""+e.message)
                }

            })
        myFlag.value=false
        return pagedListMutableLiveData
    }


}
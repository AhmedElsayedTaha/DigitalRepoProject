package com.example.digitalrepoproject.ViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.digitalrepoproject.Models.RepoModel
import com.example.digitalrepoproject.Repositories.DataRepository

class RepoViewModel : ViewModel(){

    val dataRepository = DataRepository()

    fun getRepoModel():LiveData<ArrayList<RepoModel>>{
      val  repoLiveData = dataRepository.getData()
        return repoLiveData


    }

    fun getPagingData():MutableLiveData<ArrayList<RepoModel>>{
        val  repoLiveData = dataRepository.performPagination()
        return repoLiveData
    }
}
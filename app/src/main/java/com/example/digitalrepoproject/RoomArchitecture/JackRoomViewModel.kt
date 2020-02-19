package com.example.digitalrepoproject.RoomArchitecture

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.digitalrepoproject.Models.RepoModel

class JackRoomViewModel(context: Context) :ViewModel(){

    private val repository: JackRoomRepository
    init {
        val repoDao = ReposRoomDatabase.getInstance(context).repoDao()
        repository = JackRoomRepository(repoDao)
    }

    fun getJackReposFromDb():LiveData<List<RepoEntity>>{
        return repository.getJackReposFromRoomDB
    }

    fun insertReposToDb(repoEntity: RepoEntity){
        repository.insertReposToRoomDB(repoEntity)
    }

}
package com.example.digitalrepoproject.RoomArchitecture

import androidx.lifecycle.LiveData
import com.example.digitalrepoproject.Models.RepoModel

class JackRoomRepository(private val repoDao: RepoDao) {

   var getJackReposFromRoomDB:LiveData<List<RepoEntity>> = repoDao.getJakeRepositories()

    fun insertReposToRoomDB(repoEntity: RepoEntity){
        repoDao.insertJakeRepositories(repoEntity)
    }

}
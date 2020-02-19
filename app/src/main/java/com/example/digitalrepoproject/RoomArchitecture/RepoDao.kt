package com.example.digitalrepoproject.RoomArchitecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digitalrepoproject.Models.RepoModel

@Dao
interface RepoDao {

    @Query("SELECT * FROM repoTable")
    fun getJakeRepositories():LiveData<List<RepoEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertJakeRepositories( repoEntity: RepoEntity)

}
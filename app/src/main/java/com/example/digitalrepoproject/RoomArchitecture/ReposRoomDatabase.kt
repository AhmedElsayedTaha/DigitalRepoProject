package com.example.digitalrepoproject.RoomArchitecture

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =  arrayOf(RepoEntity::class),version = 1,exportSchema = false)
abstract class ReposRoomDatabase :RoomDatabase(){
    abstract fun repoDao():RepoDao

    companion object{
        @Volatile
        private var instance : ReposRoomDatabase?=null

        fun getInstance(context: Context):ReposRoomDatabase{

            val dbInstance = instance
            if(dbInstance!=null)
                return dbInstance

             synchronized(this){
                val newInstance = Room.databaseBuilder(
                     context.applicationContext,
                     ReposRoomDatabase::class.java,
                     "jackRepoDatabase"
                 ).build()
                 instance = newInstance
                 return newInstance
             }



        }

        fun destroyDataBase(){
            instance = null
        }
    }
}
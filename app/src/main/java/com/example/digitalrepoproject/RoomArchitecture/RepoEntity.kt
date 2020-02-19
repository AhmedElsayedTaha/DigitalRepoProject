package com.example.digitalrepoproject.RoomArchitecture

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repoTable")
 data class RepoEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "fullName")
    var fullName:String,
    @ColumnInfo(name = "imageUrl")
    var imageUrl:String,
    @ColumnInfo(name = "webSite")
    var webSite:String
)
package com.example.digitalrepoproject.Network

import com.example.digitalrepoproject.Models.RepoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {
    @GET("repos")
    fun getRepo(@Query("page")page:Int ,
                @Query("per_page") perPage:Int): Single<ArrayList<RepoModel>>
}
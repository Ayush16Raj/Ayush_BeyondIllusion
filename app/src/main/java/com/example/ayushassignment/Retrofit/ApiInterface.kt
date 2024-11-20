package com.example.ayushassignment.Retrofit

import com.example.ayushassignment.model.RepoData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("users/{username}/repos")
    suspend fun getRepos(
        @Path("username") username: String
    ): List<RepoData>
}
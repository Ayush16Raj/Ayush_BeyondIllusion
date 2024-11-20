package com.example.ayushassignment.repository

import com.example.ayushassignment.Retrofit.ApiInterface
import com.example.ayushassignment.model.RepoData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class GitHubRepository @Inject constructor(private val api: ApiInterface) {

    suspend fun getRepos(username: String): List<RepoData> {
        return api.getRepos(username)
    }
}

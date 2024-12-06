package com.example.ayushassignment.repository

import android.content.Context
import com.example.ayushassignment.NetworkUtils
import com.example.ayushassignment.Retrofit.ApiInterface
import com.example.ayushassignment.model.RepoData
import com.example.ayushassignment.room.RoomDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GitHubRepository @Inject constructor(private val api: ApiInterface,
                                           private val roomDao: RoomDao,
                                           @ApplicationContext private val context: Context
) {

    suspend fun getRepos(username: String): List<RepoData> {
        return if (NetworkUtils.isInternetAvailable(context)) {
            // Fetch from API
            val items = api.getRepos(username)
            // Cache the data
            roomDao.insertItems(items)
            items
        } else {
            // Fetch from cache
            roomDao.getAllItems()
        }
    }
}

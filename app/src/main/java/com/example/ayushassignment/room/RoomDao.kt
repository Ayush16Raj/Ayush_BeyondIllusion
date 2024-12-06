package com.example.ayushassignment.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ayushassignment.model.RepoData

@Dao
interface RoomDao {
    @Query("SELECT * FROM repoData")
    suspend fun getAllItems(): List<RepoData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<RepoData>)
}
package com.example.ayushassignment.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ayushassignment.model.RepoData

@Database(entities = [RepoData::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun roomDao(): RoomDao
}
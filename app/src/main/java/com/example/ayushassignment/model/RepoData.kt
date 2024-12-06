package com.example.ayushassignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repoData")
data class RepoData(
    @PrimaryKey
    val id: Int,
    val name: String?,
    val description: String?,
    val language: String?,
    val stargazers_count: String?
)
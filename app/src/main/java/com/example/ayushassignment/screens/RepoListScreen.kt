package com.example.ayushassignment.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ayushassignment.model.RepoData

@Composable
fun RepoListScreen(repos:List<RepoData>) {
    LazyColumn{
        items(repos){repo->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                 elevation = CardDefaults.cardElevation(
                     defaultElevation = 8.dp
                 )) {
                Column(modifier = Modifier.padding(16.dp)) {
                    repo.name?.let { Text(text = it, style = MaterialTheme.typography.headlineMedium) }
                    repo.description?.let { Text(text = it, style = MaterialTheme.typography.bodyMedium) }
                    repo.language?.let { Text(text = it, style = MaterialTheme.typography.displaySmall) }

                }
            }

        }
    }
    
}
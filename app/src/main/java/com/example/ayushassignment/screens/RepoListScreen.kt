package com.example.ayushassignment.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ayushassignment.R
import com.example.ayushassignment.model.RepoData
import com.example.ayushassignment.progressBar
import com.example.ayushassignment.viewmodel.GitHubViewModel

@Composable
fun RepoListScreen(
    viewModel: GitHubViewModel,
    username: String,
    onBack: () -> Unit // Callback for back navigation
) {
    val isLoading by viewModel.isLoading.collectAsState() //collecting stateflow from kotlin coroutines and converting it into compose object
    val error by viewModel.error.collectAsState()
    val repos by viewModel.repos.collectAsState()

    Scaffold(topBar = {
        TopBar(title = username,
            onBack = onBack)
    }) {innerPadding->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(color = MaterialTheme.colorScheme.primary)
        ) {
            if(isLoading){
                progressBar()
            }
            else if (error != null) {
                Text(
                    text = error!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(modifier = Modifier.weight(1f)
                  ) {
                    items(repos) { repo ->
                        RepoItem(repo)
                    }
                }
            }

        }
    }
}

@Composable
fun RepoItem(repo: RepoData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(.5.dp, MaterialTheme.colorScheme.secondary))
            , colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            repo.name?.let { Text(text = it,
                color= MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            ) }
            repo.description?.let { Text(text = it,
                color= MaterialTheme.colorScheme.secondary) }
            Row(modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically) {
                repo.language?.let { Text(text = it,
                    color= MaterialTheme.colorScheme.secondary,)}
                Spacer(modifier = Modifier.width(40.dp))
                Icon(imageVector = Icons.Filled.Star, contentDescription = "star")
                repo.stargazers_count?.let { Text(text = it,
                    color= MaterialTheme.colorScheme.secondary)}
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title:String,
           onBack: () -> Unit) {

    // Choose drawable resources based on the theme
    val githubIcon = if (isSystemInDarkTheme()) painterResource(id = R.drawable.github_icon_dark) else painterResource(id = R.drawable.github_icon_light)

            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = {
                    Text(title)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                   Icon(painter = githubIcon, contentDescription = "GithubIcon",
                       Modifier.size(35.dp))
                }


            )
        }


package com.example.ayushassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ayushassignment.screens.RepoListScreen
import com.example.ayushassignment.screens.SearchScreen
import com.example.ayushassignment.ui.theme.AyushAssignmentTheme
import com.example.ayushassignment.viewmodel.GitHubViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AyushAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
               GitHubMainScreen()
                }
            }
        }
    }
}

@Composable
fun GitHubMainScreen(viewModel: GitHubViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val repos by viewModel.repos.collectAsState() //collecting stateflow from kotlin coroutines and converting it into compose object
    val error by viewModel.error.collectAsState()
    var query by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchScreen(
            query = query,
            onQueryChanged = { query = it },
            onSearch = { viewModel.fetchRepos(query) }
        )

        if (error != null) {
            Text(
                text = error!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            RepoListScreen(repos = repos)
        }
    }
}




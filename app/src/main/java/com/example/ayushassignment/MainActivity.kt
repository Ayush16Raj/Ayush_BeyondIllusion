package com.example.ayushassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ayushassignment.screens.RepoListScreen
import com.example.ayushassignment.screens.SearchScreen
import com.example.ayushassignment.ui.theme.AyushAssignmentTheme
import com.example.ayushassignment.viewmodel.GitHubViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: GitHubViewModel by viewModels()
        setContent {
            AyushAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                     GitHubNavGraph(viewModel)
                }
            }
        }
    }
}

@Composable
fun progressBar(){
    Box(
        modifier = Modifier.fillMaxSize(), // Make the box take full screen
        contentAlignment = Alignment.Center // Center content inside the box
    ){
    CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
}
}
@Composable
fun GitHubNavGraph(viewModel: GitHubViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "search_screen"
    ) {
        // SearchScreen Route
        composable("search_screen") {
            SearchScreen { username ->
                viewModel.fetchRepos(username) // Fetch repositories
                navController.navigate("repo_list_screen/$username") // Pass username
            }
        }

        // RepoListScreen Route
        composable(
            route = "repo_list_screen/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            RepoListScreen(
                viewModel = viewModel,
                username = username,
                onBack = { navController.popBackStack() }
            )
        }
    }
}




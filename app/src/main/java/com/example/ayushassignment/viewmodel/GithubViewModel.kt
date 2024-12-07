package com.example.ayushassignment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ayushassignment.model.RepoData
import com.example.ayushassignment.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubViewModel @Inject constructor(
    private val repository: GitHubRepository) : ViewModel() {
    private val _repos = MutableStateFlow<List<RepoData>>(emptyList()) //it is private so it can only be modified internally inside viewmodel
    val repos: StateFlow<List<RepoData>> = _repos // it ensure that external code(UI) can observe changes but cannot modify the state directly
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchRepos(username: String) {
        viewModelScope.launch {
            _isLoading.value = true   //progressbar show
            try {
                _error.value = null
                val response = repository.getRepos(username)
                _repos.value = response
                if (response.isEmpty()){
                    _error.value = "No Repository Found" // Handle if no repository found
                }
            } catch (e: Exception) {
                Log.e("Error", e.toString())
                _error.value = "Failed to load repositories."
                _repos.value = emptyList()
            } finally {
                _isLoading.value = false// progress bar hide
            }
        }
    }
    fun onSort(){
        _repos.value = _repos.value.sortedByDescending { it.name}
    }

}

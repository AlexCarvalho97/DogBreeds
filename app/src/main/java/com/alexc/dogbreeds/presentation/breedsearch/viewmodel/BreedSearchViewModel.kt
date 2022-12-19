package com.alexc.dogbreeds.presentation.breedsearch.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexc.dogbreeds.common.Resource
import com.alexc.dogbreeds.domain.repository.IBreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BreedSearchViewModel @Inject constructor(
    private val repository: IBreedsRepository
) : ViewModel() {

    var state by mutableStateOf(BreedSearchState())

    private var searchJob: Job? = null

    fun onEvent(event: BreedSearchEvent) {
        when (event) {
            is BreedSearchEvent.OnSearch -> {
                if (event.searchWord.isEmpty()) {
                    state = BreedSearchState()
                } else {
                    state = state.copy(searchWord = event.searchWord)
                    // Cancel Previous search
                    searchJob?.cancel()
                    // Set queue for Next one
                    searchJob = viewModelScope.launch {
                        delay(500L)
                        searchBreed()
                    }
                }
            }
        }
    }


    private fun searchBreed(search: String = state.searchWord) {
        viewModelScope.launch {
            repository
                .getBreedsForKeyword(search)
                .collectLatest { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { breeds ->
                                state = state.copy(breedList = breeds)
                            }
                        }
                        is Resource.Error -> {
                            state = state.copy(isError = true, errorMessage = result.message ?: "")
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }

    }
}
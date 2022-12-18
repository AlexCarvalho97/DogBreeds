package com.alexc.dogbreeds.presentation.breedlist.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexc.dogbreeds.common.Resource
import com.alexc.dogbreeds.data.repository.BreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val repository: BreedsRepository
) : ViewModel() {

    var state by mutableStateOf(BreedListState())


    init {
        // Load first page
        loadBreeds()
    }

    /**
     * Receive events from Ui
     */
    fun onEvent(event: BreedListEvent) {
        when (event) {
            is BreedListEvent.OnLoadMore -> {
                Log.d("BreedListViewModel", "load page " + state.page)
                loadBreeds(state.page)
            }

            is BreedListEvent.OnRefresh -> {
                state = state.copy(breedList = emptyList())
                state.page = 0
                loadBreeds()
            }

            is BreedListEvent.OnToggleFilter ->
            {
                // We clean the previous list and make a new request with the new filter
                state = state.copy(
                    isAscending = !state.isAscending,
                    breedList = emptyList())
                loadBreeds()
            }
            is BreedListEvent.OnChangeListType -> {
                // Update list type
                state = state.copy(listType = event.listType)
            }
        }
    }


    private fun loadBreeds(page: Int = 0) {
        viewModelScope.launch {
            repository
                .getBreeds(page = page, asc = state.isAscending)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            if (!result.fromCache) {
                                if (!result.data.isNullOrEmpty()) {
                                    if (state.page == 0) {
                                        // if is the first page and its not cache clean the previous list to set the new updated one
                                        state = state.copy(breedList = emptyList())
                                    }
                                    state.page++
                                }
                            }

                            result.data?.let { breeds ->
                                val list = state.breedList + breeds
                                state = state.copy(breedList = list)
                            }
                        }
                        is Resource.Error -> {
                            state = state.copy(error = result.message)
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

}
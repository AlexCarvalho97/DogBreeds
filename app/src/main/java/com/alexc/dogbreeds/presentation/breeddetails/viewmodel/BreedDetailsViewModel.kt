package com.alexc.dogbreeds.presentation.breeddetails.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexc.dogbreeds.common.Resource
import com.alexc.dogbreeds.data.repository.BreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BreedsRepository
) : ViewModel() {

    var state by mutableStateOf(BreedDetailsState())

    init {
        viewModelScope.launch {
            val breedId = savedStateHandle.get<Int>("breedId") ?: return@launch
            repository
                .getBreed(breedId)
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> state = state.copy(isLoading = true)
                        is Resource.Success -> {
                            result.data?.let { breed ->
                                val image = repository.getBreedImage(breed.imageId)
                                result.data.copy(image = image).also { breedWithImage ->
                                    state = state.copy(breed = breedWithImage)
                                }
                            }
                        }
                        is Resource.Error -> {
                            if (state.breed == null) {
                                state = state.copy(isError = true)
                            }
                        }
                    }
                }
        }
    }
}

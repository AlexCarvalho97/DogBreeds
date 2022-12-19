package com.alexc.dogbreeds.presentation.breeddetails.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexc.dogbreeds.common.ErrorResponse
import com.alexc.dogbreeds.common.Resource
import com.alexc.dogbreeds.domain.repository.IBreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: IBreedsRepository
) : ViewModel() {

    var state by mutableStateOf(BreedDetailsState())

    private val _errorMessage = Channel<ErrorResponse>()
    val errorMessage = _errorMessage.receiveAsFlow()

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
                                if (breed.image.imageUrl.isNullOrEmpty()) {
                                    val image = repository.getBreedImage(breed.imageId)
                                    result.data.copy(image = image).also { breedWithImage ->
                                        state = state.copy(breed = breedWithImage)
                                    }
                                } else {
                                    state = state.copy(breed = breed)
                                }
                            }
                        }
                        is Resource.Error -> {
                            _errorMessage.send(ErrorResponse(result.message ?: ""))
                        }
                    }
                }
        }
    }
}

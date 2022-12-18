package com.alexc.dogbreeds.presentation.breedsearch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.dogbreeds.presentation.breedsearch.composes.BreedSearchCard
import com.alexc.dogbreeds.presentation.breedsearch.composes.SearchInfoView
import com.alexc.dogbreeds.presentation.breedsearch.viewmodel.BreedSearchEvent
import com.alexc.dogbreeds.presentation.breedsearch.viewmodel.BreedSearchViewModel
import com.alexc.dogbreeds.ui.theme.PrimaryLight
import com.alexc.dogbreeds.ui.theme.SurfaceLight

@Composable
fun BreedSearchScreen(
    navController: NavController,
    viewModel: BreedSearchViewModel = hiltViewModel()
) {

    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        OutlinedTextField(
            value = state.searchWord,
            onValueChange = {
                viewModel.onEvent(BreedSearchEvent.OnSearch(it))
            },
            trailingIcon = {
                if (state.searchWord.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "Clear",
                        tint = PrimaryLight,
                        modifier = Modifier.clickable {
                            viewModel.onEvent(BreedSearchEvent.OnSearch(""))
                        }
                    )
                } else {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Search",
                        tint = PrimaryLight
                    )
                }
            },
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.CenterHorizontally),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = SurfaceLight,
                focusedBorderColor = PrimaryLight,
                cursorColor = PrimaryLight
            )
        )

        LazyColumn(
            modifier = Modifier.padding(horizontal = 25.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){
            items(state.breedList.size) {
                BreedSearchCard(breedItem = state.breedList[it], navController = navController)
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-60).dp)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(color = PrimaryLight)
                }
                state.isError -> {
                    if(state.breedList.isEmpty()) {
                        SearchInfoView(message = state.errorMessage)
                    }
                }
                state.breedList.isEmpty() -> {
                    if (state.searchWord.isEmpty()) {
                        SearchInfoView(
                            icon = Icons.Rounded.Create,
                            message = "Type something"
                        )
                    } else {
                        SearchInfoView(message = "No Breeds found")
                    }
                }
            }
        }
    }
}
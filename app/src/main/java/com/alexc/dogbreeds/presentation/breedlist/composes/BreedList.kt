package com.alexc.dogbreeds.presentation.breedlist.composes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.dogbreeds.domain.models.Breed
import com.alexc.dogbreeds.presentation.breedlist.composes.gridview.BreedGridViewRow
import com.alexc.dogbreeds.presentation.breedlist.composes.listview.BreedListViewCard
import com.alexc.dogbreeds.presentation.breedlist.viewmodel.BreedListEvent
import com.alexc.dogbreeds.presentation.breedlist.viewmodel.BreedListViewModel
import com.alexc.dogbreeds.presentation.breedlist.viewmodel.ListType
import com.alexc.dogbreeds.ui.theme.PrimaryLight
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun BreedList(
    breeds: List<Breed> = emptyList(),
    navController: NavController,
    viewModel: BreedListViewModel = hiltViewModel()
) {
    // Deprecated but PullToRefresh is not working properly yet and it is experimental
    val swipeRefreshState = rememberSwipeRefreshState(viewModel.state.isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            viewModel.onEvent(BreedListEvent.OnRefresh)
        },
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                // Pass the SwipeRefreshState + trigger through
                state = state,
                refreshTriggerDistance = trigger,
                contentColor = PrimaryLight
            )
        }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

                when(viewModel.state.listType){
                    ListType.GRID -> {
                        val itemCount = if (breeds.size % 2 == 0) {
                            breeds.size / 2
                        } else {
                            breeds.size / 2 + 1
                        }

                        items(itemCount) {
                            if (it >= itemCount - 1) {
                                LaunchedEffect(key1 = true) {
                                    viewModel.onEvent(BreedListEvent.OnLoadMore)
                                }
                            }

                            BreedGridViewRow(
                                rowIndex = it,
                                breedList = breeds,
                                navController = navController)
                        }
                    }


                    ListType.LIST -> {
                        items(breeds.count()) {
                            if (it >= breeds.count() - 1) {
                                LaunchedEffect(key1 = true) {
                                    viewModel.onEvent(BreedListEvent.OnLoadMore)
                                }
                            }
                            BreedListViewCard(breeds[it], navController)
                        }
                    }
                }

            item {
                if (viewModel.state.isLoading && viewModel.state.page != 0) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.Center),
                            color = PrimaryLight
                        )
                    }
                }
            }
        }
    }
}
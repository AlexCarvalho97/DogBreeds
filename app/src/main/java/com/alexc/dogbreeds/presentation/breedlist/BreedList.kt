package com.alexc.dogbreeds.presentation.breedlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.dogbreeds.presentation.breedlist.composes.listview.ListViewType
import com.alexc.dogbreeds.presentation.breedlist.viewmodel.BreedListViewModel
import com.alexc.dogbreeds.presentation.breedlist.viewmodel.ListType

@Composable
fun BreedList(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: BreedListViewModel = hiltViewModel()
) {
    val breedListState = viewModel.state
    Column(
        modifier = modifier.padding(horizontal = 25.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {

        // Display List according to type
        when (breedListState.listType) {
            is ListType.GRID -> TODO()
            is ListType.LIST -> ListViewType(breedListState.breedList, navController)
        }
    }
}

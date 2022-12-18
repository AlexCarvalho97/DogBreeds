package com.alexc.dogbreeds.presentation.breedlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.dogbreeds.presentation.breedlist.composes.BreedList
import com.alexc.dogbreeds.presentation.breedlist.composes.OptionsBottomSheetContent
import com.alexc.dogbreeds.presentation.breedlist.viewmodel.BreedListEvent
import com.alexc.dogbreeds.presentation.breedlist.viewmodel.BreedListViewModel
import com.alexc.dogbreeds.ui.theme.BackgroundColorLight
import com.alexc.dogbreeds.ui.theme.PrimaryLight
import com.alexc.dogbreeds.ui.theme.SurfaceLight
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BreedListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: BreedListViewModel = hiltViewModel()
) {
    val breedListState = viewModel.state

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            OptionsBottomSheetContent(
                listType = breedListState.listType,
                isAsc = breedListState.isAscending,
                onListType = {
                    viewModel.onEvent(BreedListEvent.OnChangeListType(it))
                },
                onToggleAsc = {
                    viewModel.onEvent(BreedListEvent.OnToggleFilter)
                }
            )
        },
        backgroundColor = BackgroundColorLight,
        sheetElevation = 23.dp,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetBackgroundColor = SurfaceLight,
        sheetPeekHeight = 0.dp
    ) {

        Column(
            modifier = modifier.padding(horizontal = 25.dp),
            verticalArrangement = Arrangement.spacedBy(35.dp)
        ) {

            Spacer(modifier = Modifier.height(15.dp))

            Row{
                Text(
                    text = "Discover Dog Breeds",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(3f)
                )

                Icon(
                    imageVector = Icons.Rounded.Tune,
                    tint = PrimaryLight,
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            scope.launch {
                                if(sheetState.isCollapsed) {
                                    sheetState.expand()
                                } else {
                                    sheetState.collapse()
                                }
                            }
                        }
                )
            }


            // Display List according to type
            BreedList(breedListState.breedList, navController)
        }
    }
}
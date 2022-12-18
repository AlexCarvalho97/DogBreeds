package com.alexc.dogbreeds.presentation.breedlist.composes

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexc.dogbreeds.presentation.breedlist.composes.bottomsheet.ButtonSheetButton
import com.alexc.dogbreeds.presentation.breedlist.viewmodel.ListType
import com.alexc.dogbreeds.ui.theme.SecondaryLight


@Composable
fun OptionsBottomSheetContent(
    listType: ListType = ListType.LIST,
    isAsc: Boolean = true,
    onListType: (ListType) -> Unit,
    onToggleAsc: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 15.dp),
    ) {


        Icon(
            imageVector = Icons.Rounded.ExpandMore,
            tint = SecondaryLight,
            contentDescription = "",
            modifier = Modifier
                .align(CenterHorizontally)
                .size(40.dp)
        )

        Spacer(modifier = Modifier.height(0.dp))

        Text(
            text = "View",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row {

            ButtonSheetButton(
                Icons.Rounded.TableRows,
                listType == ListType.LIST
            ) {
                onListType(ListType.LIST)
            }

            Spacer(modifier = Modifier.width(10.dp))

            ButtonSheetButton(
                Icons.Rounded.GridView,
                listType == ListType.GRID
            ) {
                onListType(ListType.GRID)
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Filter",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row {

            ButtonSheetButton(
                Icons.Rounded.ArrowUpward,
                isAsc
            ) {
                onToggleAsc()
            }

            Spacer(modifier = Modifier.width(10.dp))

            ButtonSheetButton(
                Icons.Rounded.ArrowDownward,
                !isAsc
            ) {
                onToggleAsc()
            }
        }
    }
}
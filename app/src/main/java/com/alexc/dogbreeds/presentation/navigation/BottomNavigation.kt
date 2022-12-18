package com.alexc.dogbreeds.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.alexc.dogbreeds.ui.theme.PrimaryLight

sealed class BottomBarItem(
    val navigation: String,
    val iconId: ImageVector
) {
    object Home : BottomBarItem(
        navigation = "breed_list",
        iconId = Icons.Rounded.Home
    )

    object Search : BottomBarItem(
        navigation = "breed_search",
        iconId = Icons.Rounded.Search
    )
}


@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        BottomBarItem.Home,
        BottomBarItem.Search,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(backgroundColor = Color.White) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination
            ) { navigation ->
                if (currentDestination?.hierarchy?.any {
                        it.route == navigation
                    } != true) {
                    navController.navigate(navigation) {
                        navController.graph.startDestinationRoute?.let { route ->
                            // Save state of the view
                            popUpTo(route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarItem,
    currentDest: NavDestination?,
    onSelectedChange: (String) -> Unit
) {
    BottomNavigationItem(
        icon = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = screen.iconId, contentDescription = "Nav icon",
                    modifier = Modifier.size(30.dp)
                )

                if (currentDest?.hierarchy?.any {
                        it.route == screen.navigation
                    } == true) {
                    Box(
                        modifier = Modifier
                            .size(width = 15.dp, height = 2.dp)
                            .background(PrimaryLight)
                    )
                }
            }
        },
        selected = currentDest?.hierarchy?.any {
            it.route == screen.navigation
        } == true,
        onClick = {
            onSelectedChange(screen.navigation)
        },
        selectedContentColor = PrimaryLight,
        unselectedContentColor = Color.LightGray
    )
}


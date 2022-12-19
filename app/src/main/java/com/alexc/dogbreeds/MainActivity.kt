package com.alexc.dogbreeds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alexc.dogbreeds.presentation.BreedDetailsScreen
import com.alexc.dogbreeds.presentation.breedlist.BreedListScreen
import com.alexc.dogbreeds.presentation.breedsearch.BreedSearchScreen
import com.alexc.dogbreeds.presentation.navigation.BottomBar
import com.alexc.dogbreeds.ui.theme.DogBreedsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DogBreedsTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val bottomBarEnabled = rememberSaveable { (mutableStateOf(true)) }

                when (navBackStackEntry?.destination?.route) {
                    "breed_details/{breedId}" -> bottomBarEnabled.value = false
                    else -> bottomBarEnabled.value = true
                }

                Scaffold(
                    bottomBar = {
                        if (bottomBarEnabled.value) {
                            BottomBar(navController)
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(androidx.compose.ui.graphics.Color.White)
                ) { padding ->

                    NavHost(
                        navController = navController,
                        startDestination = "breed_list",
                        modifier = Modifier.padding(padding)
                    ) {

                        composable("breed_list") {
                            BreedListScreen(navController)
                        }

                        composable("breed_search") {
                            BreedSearchScreen(navController)
                        }

                        composable("breed_details/{breedId}", arguments = listOf(navArgument("breedId") {
                            type = NavType.IntType
                        })) {
                            remember {
                                it.arguments?.getInt("breedId")
                            }?.let {
                                BreedDetailsScreen(breedId = it, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}
package com.themealdb.blsa.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.themealdb.blsa.presentation.mainList.MainListScreen
import com.themealdb.blsa.presentation.mealDetail.MealDetailScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavActions.MAIN_LIST_VIEW,
    navActions: NavActions = remember(navController) {
        NavActions(navController)
    }
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(NavActions.MAIN_LIST_VIEW) {
            MainListScreen(navActions)
        }

        composable(
            NavActions.MEAL_DETAIL_ROUTE,
            arguments = listOf(
                navArgument(NavActions.MEAL_ID_ARG) { type = NavType.StringType; defaultValue = "" })
        ) {
            val strTitle = it.arguments?.getString(NavActions.MEAL_ID_ARG)!!
            MealDetailScreen(navActions,strTitle)
        }
    }
}
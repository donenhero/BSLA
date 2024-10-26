package com.themealdb.blsa.presentation

import android.app.Activity
import android.content.Context
import androidx.navigation.NavController

class NavActions (private val navController: NavController) {

    companion object {
        //Screens
        const val MAIN_LIST_VIEW = "MAIN_LIST_COMPOSE_VIEW"
        const val SEARCH_VIEW = "SEARCH_COMPOSE_VIEW"
        const val MEAL_DETAIL_VIEW = "MEAL_DETAIL_COMPOSE_VIEW"
        //Args
        const val MEAL_ID_ARG = "MEAL_ID_ARG"
        //Destinations
        const val MEAL_DETAIL_ROUTE = "$MEAL_DETAIL_VIEW?$MEAL_ID_ARG={$MEAL_ID_ARG}"
    }

    fun navigateToDetail(mealId: String) {
        navController.navigate("$MEAL_DETAIL_VIEW?$MEAL_ID_ARG=$mealId")
    }
    fun navigateToSearch() {
        navController.navigate(SEARCH_VIEW)
    }
    fun popBackStack(){
        navController.popBackStack()
    }

    fun finishApp(){
        (navController.context as? Activity)?.finish()
    }

    fun getContext(): Context {
        return navController.context
    }


}
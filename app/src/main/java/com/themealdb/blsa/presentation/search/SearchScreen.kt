package com.themealdb.blsa.presentation.search


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.themealdb.blsa.R
import com.themealdb.blsa.presentation.NavActions
import com.themealdb.blsa.presentation.custom.Input
import com.themealdb.blsa.presentation.custom.Pic
import com.themealdb.blsa.presentation.custom.StyleGuide
import com.themealdb.blsa.presentation.mainList.ErrorView
import com.themealdb.blsa.presentation.mainList.LoadingView
import com.themealdb.blsa.presentation.mainList.SuccessView
import com.themealdb.blsa.utils.Status

@Composable
fun SearchScreen(
    navActions: NavActions,
    viewModel: SearchViewModel = hiltViewModel()
)
{
    Column(
        Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var searchStr by rememberSaveable { mutableStateOf("") }

        Row(Modifier.fillMaxWidth().padding(StyleGuide.sdp5()),verticalAlignment = Alignment.CenterVertically) {
            Pic.IconClickable(
                resIcon = R.drawable.ic_back,
                modifier = Modifier
                    .padding(StyleGuide.sdp5())
                    .size(dimensionResource(id = R.dimen._30sdp)),
                tInt = colorResource(R.color.colorB)
            ){
                viewModel.hideInputKeyboard(navActions.getContext())
                navActions.popBackStack()
            }

            //Search input
            Input.InputSearch(
                label = stringResource(id = R.string.search),
                inputValue = searchStr,
                modifier = Modifier.fillMaxWidth(),
                onInputChange = {
                    searchStr = it
                    if (it.isNotEmpty())
                        viewModel.getMealListFlowData(searchStr)
                },isFocus = searchStr.isEmpty()){
                viewModel.hideInputKeyboard(navActions.getContext())
            }
        }


        val requestMealList = viewModel.mealListFlowData.collectAsState().value

        if(searchStr.isNotEmpty()) {
            when (requestMealList.status) {
                Status.SUCCESS -> SuccessView(mealList = requestMealList.data!!, navActions,viewModel)
                Status.LOADING -> LoadingView()
                Status.ERROR -> ErrorView(strError = requestMealList.message ?: "") {
                    viewModel.getMealListFlowData(searchStr)
                }
            }
        }

        BackHandler {
            viewModel.hideInputKeyboard(navActions.getContext())
            navActions.popBackStack()
        }

    }

}
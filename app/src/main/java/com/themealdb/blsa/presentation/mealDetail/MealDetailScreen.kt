package com.themealdb.blsa.presentation.mealDetail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.themealdb.blsa.R
import com.themealdb.blsa.presentation.NavActions
import com.themealdb.blsa.presentation.custom.Label
import com.themealdb.blsa.presentation.custom.Pic
import com.themealdb.blsa.presentation.custom.SpecialtyViews
import com.themealdb.blsa.presentation.custom.StyleGuide

@Composable
fun MealDetailScreen(navActions: NavActions,
                     mealId:String,
                     viewModel: MealDetailViewModel = hiltViewModel()
)
{
    LaunchedEffect(mealId) {
        viewModel.getMealDetail(mealId)
    }

    Column{
        val item = viewModel.mealListFlowData.collectAsState().value

        SpecialtyViews.Header(item.strMeal, sharedInterface =  viewModel, mealId = mealId){
            navActions.popBackStack()
        }
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .background(colorResource(id = R.color.white))
                .padding(StyleGuide.sdp5(),0.dp))
        {

            Pic.AsyncImageView(
                imgUrl = item.strMealThumb ?: "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, StyleGuide.sdp10())
                    .clip(StyleGuide.rounded5sdp())
                    .fillMaxWidth()
            )

            Row(Modifier.fillMaxWidth()) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Label.TextBold(
                        strContent = item.strArea ?: "",
                        resSize = R.dimen._11sdp,
                        resTextColor = R.color.colorAccent,
                        resPaddingH = R.dimen._5sdp,
                        resPaddingV = R.dimen._1sdp,
                        textAlign = TextAlign.Start
                    )

                    Label.TextBold(
                        strContent = (item.strTags ?: "") + " " + (item.strCategory ?: ""),
                        resSize = R.dimen._11sdp,
                        resTextColor = R.color.colorB,
                        resPaddingH = R.dimen._1sdp,
                        resPaddingV = R.dimen._1sdp,
                        textAlign = TextAlign.Start
                    )
                }
            }

            HorizontalDivider(color = colorResource(id = R.color.colorAccent),
                modifier = Modifier
                    .padding(0.dp, StyleGuide.sdp5())
                    .fillMaxWidth())

            for (i in item.getIngredients().indices){
                IngredientView(item.getIngredients()[i] ?: "",item.getMeasures()[i] ?: "")
            }

            HorizontalDivider(color = colorResource(id = R.color.colorAccent),
                modifier = Modifier
                    .padding(0.dp, StyleGuide.sdp5())
                    .fillMaxWidth())

            Label.TextBold(
                strContent = item.strInstructions ?: "",
                resSize = R.dimen._10sdp,
                resTextColor = R.color.colorGray,
                resPaddingH = R.dimen._5sdp,
                resPaddingV = R.dimen._2sdp,
                textAlign = TextAlign.Start
            )
        }
    }


    BackHandler {
        navActions.popBackStack()
    }
}

@Composable
fun IngredientView(name:String,measure:String){

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Label.TextBold(
            strContent = name,
            resSize = R.dimen._11sdp,
            resTextColor = R.color.colorB,
            resPaddingH = R.dimen._5sdp,
            resPaddingV = R.dimen._1sdp,
            textAlign = TextAlign.Start
        )

        Label.TextBold(
            strContent = measure,
            resSize = R.dimen._11sdp,
            resTextColor = R.color.colorAccent,
            resPaddingH = R.dimen._5sdp,
            resPaddingV = R.dimen._1sdp,
            textAlign = TextAlign.Start
        )
    }
}

package com.themealdb.blsa.presentation.mainList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.themealdb.blsa.R
import com.themealdb.blsa.data.local.SharedInterface
import com.themealdb.blsa.domain.model.MealItem
import com.themealdb.blsa.domain.model.MealListResult
import com.themealdb.blsa.presentation.NavActions
import com.themealdb.blsa.presentation.custom.Buttons
import com.themealdb.blsa.presentation.custom.Label
import com.themealdb.blsa.presentation.custom.Pic
import com.themealdb.blsa.presentation.custom.SpecialtyViews
import com.themealdb.blsa.presentation.custom.StyleGuide
import com.themealdb.blsa.utils.Status

/**
 * صفحه ااصلی لیست دستورپخت ها که در ابتدا درخواست دریافت لیست دستورپخت ها را به سرور ارسال کرده سپس با توجه به جواب دریافتی از سرور
 * صفحه را تنظیم و به کاربر نمایش میدهد
 * @param navActions برای کنترل و دسترسی به سایر صفحات به این صفحه پاس داده میشود
 * @param viewModel ویومدل مربوط به این صفحه
 */

@Composable
fun MainListScreen(
    navActions: NavActions,
    viewModel: MainListViewModel = hiltViewModel()
)
{
    Column(
        Modifier.background(colorResource(R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally) {


        Box(contentAlignment = Alignment.CenterEnd){
            //Search icon
            Pic.IconClickable(
                resIcon = R.drawable.search,
                modifier = Modifier
                    .padding(StyleGuide.sdp15(),StyleGuide.sdp10())
                    .size(dimensionResource(id = R.dimen._20sdp)),
                tInt = colorResource(R.color.colorB)
            ){
                navActions.navigateToSearch()
            }

            Label.TextBold(
                resText = R.string.meal_list_title,
                modifier = Modifier.fillMaxWidth(),
                resSize = R.dimen._12sdp,
                resTextColor = R.color.colorB,
                resPaddingV = R.dimen._5sdp,
                textAlign = TextAlign.Center)
        }

        // ایجاد یک آبسرور که حالات مربوط به ارسال درخواست لیست دستورپخت در آن قرار میگیرد
        val requestMealList = viewModel.mealListFlowData.collectAsState().value

        when (requestMealList.status) {
            Status.SUCCESS -> SuccessView(mealList = requestMealList.data!!, navActions,viewModel)
            Status.LOADING -> LoadingView()
            Status.ERROR -> ErrorView(strError = requestMealList.message ?: ""){
                viewModel.getMealListFlowData()
            }
        }

    }



}

/**
 * در صورتی که جواب از سمت سرور به صورت صحیح دریافت شود این متد ویو فراخوانی مشود
 * @param mealList لیست دستورپخت های دریافتی
 * @param navActions کنترل و دسترسی به سایر صفحات
 */

@Composable
fun SuccessView(mealList: MealListResult, navActions: NavActions,sharedInterface: SharedInterface){

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, dimensionResource(id = R.dimen._100sdp) ),content = {
            items(mealList.meals!!.size) {
                MealItemView(item = mealList.meals!![it],navActions,sharedInterface)
            }
        })
}

/**
 * آیتم ویو به ازای هر دستورپخت در لیست قراخوانی میشود
 * @param item آیتم دیتا دریافتی به ازای هر دستورپخت
 * @param navActions برای انتقال به صفحه جزییات دستورپخت از این پارامتر استفاده میشود
 */

@Composable
fun MealItemView(item: MealItem, navActions: NavActions,sharedInterface: SharedInterface)
{
    SpecialtyViews.CardViewWrap(dpBasePadding = StyleGuide.sdp4()){
        Row(
            Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        navActions.navigateToDetail(item.idMeal)

                    },
                    indication = ripple(bounded = true, color = Color.Black)
                )
                .background(colorResource(id = R.color.white))
                .padding(StyleGuide.sdp5()),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {

            Pic.AsyncImageView(
                imgUrl = item.strMealThumb ?: "",
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen._60sdp))
                    .clip(StyleGuide.rounded5sdp())
                    .fillMaxWidth()
            )

            Column(modifier = Modifier.padding(StyleGuide.sdp5())) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Label.TextBold(
                        strContent = item.strMeal,
                        resSize = R.dimen._12sdp,
                        resTextColor = R.color.colorB,
                        resPaddingH = R.dimen._5sdp,
                        resPaddingV = R.dimen._1sdp,
                        textAlign = TextAlign.Start
                    )

                    var isFav:Boolean by remember { mutableStateOf(sharedInterface.searchString(item.idMeal)) }

                    Pic.IconClickable(
                        resIcon = if(isFav)R.drawable.ic_star_fill else R.drawable.ic_star_nofill,
                        Modifier.size(StyleGuide.sdp15())){
                        if (isFav)
                            sharedInterface.removeString(item.idMeal)
                        else
                            sharedInterface.addString(item.idMeal)
                        isFav = !isFav
                    }
                }

                Row(Modifier.fillMaxWidth()) {
                    Label.TextBold(
                        strContent = item.strArea ?: "",
                        resSize = R.dimen._10sdp,
                        resTextColor = R.color.colorAccent,
                        resPaddingH = R.dimen._5sdp,
                        resPaddingV = R.dimen._1sdp,
                        textAlign = TextAlign.Start
                    )

                    Label.TextBold(
                        strContent = (item.strTags ?: "") + " " + (item.strCategory ?: ""),
                        resSize = R.dimen._10sdp,
                        resTextColor = R.color.colorB,
                        resPaddingH = R.dimen._1sdp,
                        resPaddingV = R.dimen._1sdp,
                        isSingleLine = true,
                        textAlign = TextAlign.Start
                    )
                }
                Label.TextBold(
                    strContent = item.getConcatenatedIngredients(),
                    resSize = R.dimen._8sdp,
                    resTextColor = R.color.colorGray,
                    resPaddingH = R.dimen._5sdp,
                    resPaddingV = R.dimen._1sdp,
                    isSingleLine = true,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}


/**
 *در صورتی که صفحه درحال انتظار برای دریافت اطلاعات دستورپختها باشد این متد فراخوانی میشود
 */
@Composable
fun LoadingView()
{
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        CircularProgressIndicator(
            modifier= Modifier
                .size(dimensionResource(id = R.dimen._52sdp)),
            color = colorResource(id = R.color.colorAccent)
        )
    }
}

/**
 *در صورتی که به هر دلیلی دریافت اطلاعات لیست دستورپختئ ها با مشکل مواجه شود این صفحه نمایش داده میشود
 */
@Composable
fun ErrorView(strError:String,eventRetry:()->Unit)
{
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Pic.ImageView(imgRes = R.drawable.img_retry, resWidth = R.dimen._180sdp, resHeight =R.dimen._134sdp )
        Label.TextBold(strContent = strError, resSize = R.dimen._10sdp)

        Buttons.ButtonWrap(
            resText = R.string.retry,
            resSize = R.dimen._12sdp,
            resTextColor =  R.color.white,
            resBackColor = R.color.colorAccent,
            resMarginTop =  R.dimen._10sdp){

            eventRetry()
        }
    }
}
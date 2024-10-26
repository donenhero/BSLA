package com.themealdb.blsa.presentation.custom

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.themealdb.blsa.R

object SpecialtyViews {

    @Composable
    fun Header(strHeader:String,
               resIcon:Int = R.drawable.ic_back,
               resTInt:Int = R.color.colorB,
               backEvent:()->Unit = {})
    {
        Box(contentAlignment = Alignment.CenterStart){
            Pic.IconClickable(
                resIcon = resIcon,
                modifier = Modifier
                    .padding(StyleGuide.sdp5())
                    .size(dimensionResource(id = R.dimen._30sdp)),
                tInt = colorResource(resTInt)
            ){
                backEvent()
            }

            Label.TextBold(
                strContent = strHeader,
                modifier = Modifier.fillMaxWidth(),
                resSize = R.dimen._12sdp,
                resTextColor = resTInt,
                resPaddingV = R.dimen._5sdp,
                textAlign = TextAlign.Center)
        }
    }

    @Composable
    fun CardViewWrap(
        resPaddingBottom:Int = R.dimen._10sdp,
        dpBasePadding: Dp = StyleGuide.sdp10(),
        content: @Composable ColumnScope.() -> Unit
    ) {
        Card(
            Modifier
                .padding(
                    dpBasePadding,
                    dpBasePadding,
                    dpBasePadding,
                    dimensionResource(id = resPaddingBottom)
                )
                .shadow(StyleGuide.sdp5(), StyleGuide.rounded10sdp())
                .wrapContentWidth()
                .background(Color.White)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {},
                    indication = null
                )
                .animateContentSize()
        ) {
            content()
        }

    }
}
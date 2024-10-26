package com.themealdb.blsa.presentation.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.themealdb.blsa.R
import com.themealdb.blsa.presentation.custom.StyleGuide.rounded5sdp
import com.themealdb.blsa.presentation.custom.StyleGuide.sdp20
import com.themealdb.blsa.presentation.custom.StyleGuide.sdp5

object Buttons {

    @Composable
    fun ButtonWrap(
        resText:Int,
        resSize:Int,
        resTextColor:Int,
        resBackColor:Int = R.color.colorAccent,
        resMarginTop:Int=R.dimen._10sdp,
        resMarginBottom:Int=R.dimen._10sdp,
        resMarginStart:Int=R.dimen._10sdp,
        resMarginEnd:Int=R.dimen._10sdp,
        borderColor: Color = Color.Transparent,
        paddingH: Dp = sdp20(),
        paddingV: Dp = sdp5(),
        borderRadios: RoundedCornerShape = rounded5sdp(),
        alpha:Float = 1.0f,
        fontFamily: FontFamily = StyleGuide.YekanFont,
        event:()-> Unit)
    {
        Text(text = stringResource(id = resText)
            , Modifier
                .padding(
                    dimensionResource(id = resMarginStart),
                    dimensionResource(id = resMarginTop),
                    dimensionResource(id = resMarginEnd),
                    dimensionResource(id = resMarginBottom)
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { if (alpha == 1.0f) event() },
                    indication = ripple(bounded = true, color = Color.White)
                )
                .alpha(alpha = alpha)
                .wrapContentWidth()
                .clip(borderRadios)
                .border(1.dp, borderColor, borderRadios)
                //.shadow(sdp1(), rounded5sdp())
                .background(colorResource(resBackColor))
                .padding(
                    paddingH, paddingV
                )
            , color = colorResource(id  = resTextColor)
            , textAlign = TextAlign.Center
            , fontFamily = fontFamily
            , fontWeight = FontWeight.Bold
            , fontSize = dimensionResource(id =resSize).value.sp)

    }
}
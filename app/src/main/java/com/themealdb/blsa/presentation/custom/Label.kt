package com.themealdb.blsa.presentation.custom

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.themealdb.blsa.R

object Label {

    @Composable
    fun TextBold(
        modifier: Modifier = Modifier,
        resText:Int,
        resSize:Int = R.dimen._11sdp,
        resTextColor:Int= R.color.colorB,
        resPaddingH:Int= R.dimen._20sdp,
        resPaddingV:Int = R.dimen._5sdp,
        textAlign: TextAlign = TextAlign.Center,
        fontFamily: FontFamily = StyleGuide.YekanFont)
    {
        TextViewNoClick(
            strContent = stringResource(id = resText),
            resSize =resSize,
            resTextColor = resTextColor,
            resPaddingH = resPaddingH,
            resPaddingV = resPaddingV,
            textAlign = textAlign,
            fontFamily = fontFamily,
            modifier = modifier
        )

    }


    @Composable
    fun TextBold(
        strContent:String,
        resSize:Int,
        modifier: Modifier = Modifier,
        resTextColor:Int= R.color.colorB,
        resPaddingH:Int= R.dimen._20sdp,
        resPaddingV:Int = R.dimen._5sdp,
        textAlign: TextAlign = TextAlign.Center,
        isStrikeText: Boolean = false,
        isSingleLine: Boolean = false,
        fontFamily: FontFamily = StyleGuide.YekanFont)
    {

        TextViewNoClick(
            strContent = strContent,
            resSize =resSize,
            resTextColor = resTextColor,
            resPaddingH = resPaddingH,
            resPaddingV = resPaddingV,
            textAlign = textAlign,
            fontFamily=fontFamily,
            isStrikeText = isStrikeText,
            maxLine = if (isSingleLine) 1 else Int.MAX_VALUE,
            modifier = modifier
        )
    }


    @Composable
    fun TextViewNoClick(
        strContent:String,
        resSize:Int,
        modifier: Modifier = Modifier,
        resTextColor:Int= R.color.colorB,
        resPaddingH:Int= R.dimen._20sdp,
        resPaddingV:Int = R.dimen._5sdp,
        fontFamily: FontFamily = StyleGuide.YekanFont,
        fontWeight: FontWeight = FontWeight.Bold,
        textAlign: TextAlign = TextAlign.Center,
        lineSpace: TextUnit = TextUnit.Unspecified,
        textDirection: TextDirection = TextDirection.Content,
        isStrikeText: Boolean = false,
        maxLine: Int = Int.MAX_VALUE,
    )
    {
        Text(text = strContent
            , modifier = modifier
                .padding(
                    dimensionResource(id = resPaddingH), dimensionResource(id = resPaddingV)
                )
            , color = colorResource(id = resTextColor)
            , maxLines = maxLine
            , overflow = TextOverflow.Ellipsis
            , lineHeight = lineSpace
            , textAlign = textAlign
            , fontFamily = fontFamily
            , fontWeight = fontWeight
            , fontSize = dimensionResource(id =resSize).value.sp
            , style = if (isStrikeText) TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle(textDirection = textDirection)
        )

    }
}
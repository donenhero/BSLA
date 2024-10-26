package com.themealdb.blsa.presentation.custom

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.themealdb.blsa.R

object StyleGuide {

    @Composable
    fun sdp1(): Dp {
        return dimensionResource(id = R.dimen._1sdp); }
    @Composable
    fun sdp2(): Dp {
        return dimensionResource(id = R.dimen._2sdp); }
    @Composable
    fun sdp3(): Dp {
        return dimensionResource(id = R.dimen._3sdp); }
    @Composable
    fun sdp4(): Dp {
        return dimensionResource(id = R.dimen._4sdp); }

    @Composable
    fun sdp5(): Dp {
        return dimensionResource(id = R.dimen._5sdp); }

    @Composable
    fun sdp10(): Dp {
        return dimensionResource(id = R.dimen._10sdp); }

    @Composable
    fun sdp15(): Dp {
        return dimensionResource(id = R.dimen._15sdp); }

    @Composable
    fun sdp20(): Dp {
        return dimensionResource(id = R.dimen._20sdp); }


    @Composable
    fun rounded5sdp(): RoundedCornerShape {
        return RoundedCornerShape(sdp5())
    }

    @Composable
    fun rounded10sdp(): RoundedCornerShape {
        return RoundedCornerShape(sdp10())
    }

    @Composable
    fun rounded15sdp(): RoundedCornerShape {
        return RoundedCornerShape(sdp15())
    }

    @Composable
    fun rounded20sdp(): RoundedCornerShape {
        return RoundedCornerShape(sdp20())
    }


    val YekanFont = FontFamily(
        Font(R.font.yekan_bakh),
        Font(R.font.yekan_bakh_bold, FontWeight.Bold),
        Font(R.font.yekan_bakh_light, FontWeight.Light))
}
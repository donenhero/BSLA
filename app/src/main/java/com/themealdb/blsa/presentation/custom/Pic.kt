package com.themealdb.blsa.presentation.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.themealdb.blsa.R

object Pic {


    @Composable
    fun IconClickable(resIcon:Int,
                      modifier: Modifier = Modifier,
                      tInt: Color = colorResource(id = R.color.colorAccent),
                      event:()->Unit ={})
    {
        Icon(imageVector = ImageVector.vectorResource(id = resIcon),
            tint = tInt,
            contentDescription ="",
            modifier = modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                onClick = { event() },
                indication = ripple(bounded = true, color = Color.Black)
            ))
    }


    @Composable
    fun ImageView(
        imgRes:Int,
        resWidth:Int,
        resHeight:Int,
        modifier: Modifier = Modifier,
        resMargin:Int= R.dimen._1sdp,
        contentScale: ContentScale = ContentScale.FillBounds,
        alpha: Float = 1.0f)
    {
        Image(
            painterResource(id = imgRes)
            ,""
            ,modifier = modifier
                .width(dimensionResource(resWidth))
                .height(dimensionResource(resHeight))
                .padding(dimensionResource(id = resMargin))

            , contentScale = contentScale, alpha = alpha)
    }


    @Composable
    fun AsyncImageView(
        imgUrl:String,
        modifier: Modifier = Modifier,
        contentScale: ContentScale = ContentScale.FillBounds,
        alpha: Float = 1.0f,
        alignment: Alignment = Alignment.Center)
    {

        AsyncImage(
            model  = ImageRequest.Builder(LocalContext.current)
                .data(imgUrl)
                .error(R.drawable.ic_placeholder)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            contentDescription = "",
            modifier = modifier,
            contentScale = contentScale,
            alpha = alpha,
            alignment = alignment)
    }
}
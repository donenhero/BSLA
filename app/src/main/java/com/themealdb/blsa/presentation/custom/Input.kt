package com.themealdb.blsa.presentation.custom

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.themealdb.blsa.R

object Input {

    @Composable
    fun InputSearch(
        label:String,
        inputValue:String,
        onInputChange:(String) -> Unit,
        isFocus:Boolean = true,
        modifier: Modifier = Modifier,
        onDoneAction:() -> Unit)
    {
        val focusRequester = remember {
            FocusRequester()
        }


        OutlinedTextField(
            value = inputValue,
            onValueChange = onInputChange,
            shape = StyleGuide.rounded5sdp(),
            modifier = modifier
                .focusRequester(focusRequester)
                .padding(StyleGuide.sdp10(), StyleGuide.sdp5())
                .fillMaxWidth()
                .wrapContentHeight(),
            textStyle = TextStyle(
                color= colorResource(id = R.color.colorB),
                fontSize= dimensionResource(id = R.dimen._11sdp).value.sp,
                fontFamily = StyleGuide.YekanFont,
                fontWeight = FontWeight.Bold
            ),
            isError = false,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = colorResource(id = R.color.white),
                unfocusedLabelColor = colorResource(id = R.color.colorBLight),

                cursorColor = colorResource(id = R.color.colorB),
                focusedBorderColor = colorResource(id = R.color.white),
                focusedLabelColor = colorResource(id = R.color.colorBLight),

                errorCursorColor = colorResource(id = R.color.red),
                errorBorderColor = colorResource(id = R.color.white),
                errorLabelColor = colorResource(id = R.color.red)

            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done).copy(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onDone = { onDoneAction() }),
            trailingIcon = {
                if (inputValue.isNotEmpty()){
                    val image = R.drawable.ic_close
                    IconButton(onClick = {onInputChange("")}){
                        Icon(
                            imageVector = ImageVector.vectorResource(id = image),
                            tint = colorResource(R.color.colorB),
                            contentDescription ="")
                    }
                }
            },
            label = { Text(label
                , fontFamily = StyleGuide.YekanFont
                , fontWeight = FontWeight.Bold
                , fontSize = dimensionResource(id = R.dimen._10sdp).value.sp)
            })

        DisposableEffect(Unit) {
            object: Handler(Looper.getMainLooper()){}.postDelayed({
                if(isFocus) {
                    focusRequester.requestFocus()
                }
            },500)
            onDispose { }
        }
    }
}
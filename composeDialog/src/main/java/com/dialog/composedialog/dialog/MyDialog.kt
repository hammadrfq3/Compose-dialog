package com.dialog.composedialog.dialog

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.dialog.composedialog.R
import com.dialog.composedialog.components.InfiniteCircularList
import com.dialog.composedialog.components.MyEditText


@Composable
fun MyDialog(
    showDialog: Boolean = true,
    title: String,
    positiveButtonText: String,
    negativeButtonText: String,
    buttonShape: Shape = RoundedCornerShape(15.dp),
    dialogBackgroundColor: Color = colorResource(id = R.color.white),
    negativeButtonBackgroundColor: Color = colorResource(id = R.color.white),
    positiveButtonBackgroundColor: Color = colorResource(id = R.color.dialog_black_btn),
    positiveButtonTextColor: Color = colorResource(id = R.color.white),
    negativeButtonTextColor: Color = colorResource(id = R.color.text_dark),
    titleTextColor: Color = colorResource(id = R.color.black),
    descriptionTextColor: Color = colorResource(id = R.color.light_grey),
    isCancelable: Boolean = true,
    isEditableDialog: Boolean = false,
    isAnimatedDialog: Boolean = false,
    isPickerDialog: Boolean = false,
    textAlign: TextAlign = TextAlign.Center,
    @RawRes lottieAnimatorRes: Int? = null,
    @DrawableRes image: Int? = null,
    description: String,
    onDismiss: () -> Unit,
    onDoneClick:  (Any) -> Unit,
) {

    var returnValue : Any? = null

    if (showDialog) {
        Dialog(
            properties = DialogProperties(
                dismissOnClickOutside = isCancelable,
                dismissOnBackPress = isCancelable
            ),
            onDismissRequest = {
                onDismiss.invoke()
            },
        ) {

            Box(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = if (image != null || lottieAnimatorRes != null) 25.dp else 0.dp)
                        .background(color = dialogBackgroundColor, RoundedCornerShape(20.dp))
                        .padding(top = if (image != null || lottieAnimatorRes != null) 50.dp else 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp),
                            text = title,
                            color = titleTextColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = textAlign
                        )
                        if (isPickerDialog) {
                            VerticalScroller{ item ->
                                returnValue = item
                            }
                        } else {
                            if (!isEditableDialog)
                                Text(
                                    modifier = Modifier
                                        .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                                    text = description,
                                    textAlign = textAlign,
                                    color = descriptionTextColor
                                )
                            else
                                MyEditText{
                                    returnValue = it
                                }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp, end = 10.dp, bottom = 10.dp, start = 10.dp)
                            .fillMaxWidth()
                            .background(color = Color.Transparent, shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        if (!isPickerDialog)
                            Button(
                                modifier = Modifier
                                    .weight(.5f)
                                    .height(45.dp),
                                onClick = {
                                    onDismiss.invoke()
                                },
                                border = BorderStroke(1.dp, color = positiveButtonBackgroundColor),
                                shape = buttonShape,
                                colors = ButtonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = Color.White,
                                    disabledContentColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent
                                )
                            ) {
                                Text(
                                    text = negativeButtonText,
                                    fontSize = 16.sp,
                                    color = negativeButtonTextColor,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        Button(
                            modifier = Modifier
                                .weight(.5f)
                                .height(45.dp),
                            onClick = {
                                returnValue?.let {
                                    onDoneClick.invoke(it)
                                } ?: kotlin.run {
                                    onDoneClick.invoke("")
                                }
                            },
                            shape = buttonShape,
                            colors = ButtonColors(
                                containerColor = positiveButtonBackgroundColor,
                                contentColor = colorResource(id = R.color.black),
                                disabledContentColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent
                            ),
                        ) {
                            Text(
                                text = positiveButtonText,
                                fontSize = 16.sp,
                                color = positiveButtonTextColor,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                if (image != null)
                    Box(
                        modifier = Modifier
                            .background(Color.White, shape = CircleShape)
                    ) {
                        Image(
                            modifier = Modifier
                                .size(62.dp)
                                .padding(6.dp),
                            alignment = Alignment.Center,
                            painter = painterResource(id = image),
                            contentDescription = ""
                        )
                    }

                if (isAnimatedDialog)
                    Box(
                        modifier = Modifier
                            .background(Color.White, shape = CircleShape)
                    ) {
                        AnimatedPreloader(lottieAnimatorRes = lottieAnimatorRes!!)
                    }
            }

        }
    }

}

@Composable
fun AnimatedPreloader(modifier: Modifier = Modifier.size(62.dp), lottieAnimatorRes: Int) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            lottieAnimatorRes
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = 1,
        isPlaying = true
    )


    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
    )
}

@Composable
fun VerticalScroller(
    onItemSelected: (item: String) -> Unit = { _ -> }
) {

    var hourValue = ""
    var minValue = ""
    var amPm = "am"
    var finalValue = ""

    val hours = (0..12).toList().map { c -> String.format("%02d", c) }
    val mins = (0..59).toList().map { c -> String.format("%02d", c) }

    Box(
        modifier = Modifier
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier
                    .padding(end = 20.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier,
                    text = "Hour",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = colorResource(id = R.color.light_grey)
                )

                InfiniteCircularList(
                    width = 200.dp,
                    itemHeight = 40.dp,
                    items = hours, //(1..30).map { it.toString() },
                    initialItem = "December",
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 25.sp),
                    textColor = colorResource(id = R.color.light_grey),
                    selectedTextColor = colorResource(id = R.color.orange),
                    onItemSelected = { i, item ->
                        hourValue = item
                        finalValue = if (minValue.isEmpty())
                            "$hourValue $amPm"
                        else
                            "$hourValue : $minValue $amPm"
                        onItemSelected.invoke(finalValue)
                    }
                )
            }

            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = ":",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                color = colorResource(id = R.color.orange)
            )


            Column(
                modifier = Modifier
                    .padding(start = 20.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier,
                    text = "Min",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = colorResource(id = R.color.light_grey)
                )
                InfiniteCircularList(
                    width = 200.dp,
                    itemHeight = 40.dp,
                    items = mins, //(1..30).map { it.toString() },
                    initialItem = "December",
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 25.sp),
                    textColor = colorResource(id = R.color.light_grey),
                    selectedTextColor = colorResource(id = R.color.orange),
                    onItemSelected = { i, item ->
                        minValue = item
                        finalValue = if (hourValue.isEmpty())
                            minValue
                        else
                            "$hourValue : $minValue $amPm"
                        onItemSelected.invoke(finalValue)
                    }
                )
            }
        }


        AMPMView(Modifier.align(Alignment.CenterEnd)){
            amPm = ""
            amPm = it
            finalValue = "$hourValue : $minValue $amPm"
            onItemSelected.invoke(finalValue)
        }
    }


}

@Composable
fun AMPMView(
    modifier: Modifier,
    onItemSelected: (String) -> Unit
){

    var isAmClicked by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = modifier
            .padding(start = 20.dp)
            .border(color = colorResource(id = R.color.black), width = 1.dp, shape = RoundedCornerShape(10.dp))
        ,
    ) {
        Text(
            modifier = Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    isAmClicked = true
                    onItemSelected.invoke("am")
                }
                .background(
                    color = if (isAmClicked) colorResource(id = R.color.black) else Color.Transparent,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                )
                .padding(8.dp)
            ,
            text = "AM",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = if (isAmClicked) colorResource(id = R.color.white) else colorResource(id = R.color.light_grey)
        )
        Text(
            modifier = Modifier
                .clickable {
                    isAmClicked = false
                    onItemSelected.invoke("pm")
                }
                .background(
                    color = if (!isAmClicked) colorResource(id = R.color.black) else Color.Transparent,
                    shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
                )
                .padding(8.dp),
            text = "PM",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = if (!isAmClicked) colorResource(id = R.color.white) else colorResource(id = R.color.light_grey)
        )
    }

}



@Composable
private fun AnimatedText(
    lazyListState: LazyListState,
    index: Int,
    item: String,
    onSelection: (String) -> Unit
) {
    var isSelected by remember { mutableStateOf(false) }
    val textColor by remember {
        derivedStateOf {
            val layoutInfo = lazyListState.layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            val itemInfo = visibleItemsInfo.firstOrNull { it.index == index }
            itemInfo?.let {
                val delta = it.size / 2 //use your custom logic
                val center = lazyListState.layoutInfo.viewportEndOffset / 2
                val childCenter = it.offset + it.size / 2
                val target = childCenter - center
                if (target in -delta..delta) {
                    isSelected = true
                    return@derivedStateOf R.color.orange
                }
            }
            isSelected = false
            R.color.grey
        }
    }

    LaunchedEffect(isSelected) {
        if (isSelected) onSelection(item)
    }
    //val isWholeNumber = item % 1.0 == 0.0
    /*val text = if (item in 100.0..250.0 || item in 3.0..9.5) {
        if (isWholeNumber) item.toInt().toString() else item.toString()
    } else ""*/
    Text(
        text = item,
        //fontSize = if (isSelected) 32.sp else 26.sp,
        fontSize = 32.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = colorResource(id = textColor),
        modifier = Modifier
            .width(70.dp)
            //.height(60.dp)
            .wrapContentHeight()
        //.drawPreviewBorder()
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyDialog(
        title = "Enter dialog title",
        description = "A dialog is a type of modal window that appears in front of app content to provide critical information, or prompt for a decision to be made.",
        positiveButtonText = "Ok",
        negativeButtonText = "Cancel"
        ,onDismiss = {
        },
        onDoneClick = {
        })
}
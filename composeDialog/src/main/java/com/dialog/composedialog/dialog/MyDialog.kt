package com.dialog.composedialog.dialog

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
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

@Composable
fun MyDialog(
    showDialog: Boolean = true,
    title: String,
    positiveButtonText: String,
    negativeButtonText: String,
    @DrawableRes dialogBackgroundColor: Int = R.color.white,
    @DrawableRes negativeButtonBackgroundColor: Int = R.color.white,
    @DrawableRes positiveButtonBackgroundColor: Int = R.color.dialog_black_btn,
    @DrawableRes positiveButtonTextColor: Int = R.color.white,
    @DrawableRes negativeButtonTextColor: Int = R.color.text_dark,
    @DrawableRes titleTextColor: Int = R.color.black,
    @DrawableRes descriptionTextColor: Int = R.color.light_grey,
    isCancelable: Boolean = true,
    isEditableDialog: Boolean = false,
    isAnimatedDialog: Boolean = false,
    isPickerDialog: Boolean = false,
    textAlign: TextAlign = TextAlign.Center,
    @DrawableRes lottieAnimatorRes: Int? = null,
    @DrawableRes image: Int? = null,
    description: String,
    onDismiss: () -> Unit,
    onDoneClick: () -> Unit,
) {


    if (showDialog) {
        Dialog(
            properties = DialogProperties(
                dismissOnClickOutside = isCancelable,
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
                        .background(color = colorResource(id = dialogBackgroundColor), RoundedCornerShape(20.dp))
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
                            color = colorResource(id = titleTextColor),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = textAlign
                        )
                        if (isPickerDialog) {
                            CircularTimer()
                        } else {
                            if (!isEditableDialog)
                                Text(
                                    modifier = Modifier
                                        .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                                    text = description,
                                    textAlign = textAlign,
                                    color = colorResource(id = descriptionTextColor)
                                )
                            else
                                MyEditText()
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp, end = 10.dp, bottom = 10.dp)
                            .fillMaxWidth()
                            .background(color = Color.Transparent, shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        if (!isPickerDialog)
                            Button(
                                modifier = Modifier
                                    .height(45.dp),
                                onClick = {
                                    onDismiss.invoke()
                                },
                                border = BorderStroke(1.dp, color = colorResource(id = positiveButtonBackgroundColor)),
                                shape = RoundedCornerShape(15.dp),
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
                                    color = colorResource(id = negativeButtonTextColor),
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        Button(
                            modifier = Modifier
                                .height(45.dp),
                            onClick = {
                                onDoneClick.invoke()
                            },
                            shape = RoundedCornerShape(15.dp),
                            colors = ButtonColors(
                                containerColor = colorResource(id = positiveButtonBackgroundColor),
                                contentColor = colorResource(id = R.color.black),
                                disabledContentColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent
                            ),
                        ) {
                            Text(
                                text = positiveButtonText,
                                fontSize = 16.sp,
                                color = colorResource(id = positiveButtonTextColor),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyEditText() {

    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            containerColor = colorResource(R.color.grey)
        ),
        placeholder = {
            Text(text = "Enter text here", color = colorResource(R.color.light_grey))
        },
    )

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
fun CircularTimer(
) {

    var userSelectedHeight by rememberSaveable { mutableStateOf("") }

    val hours = (0..12).toList().map { c -> String.format("%02d", c) }
    val mins = (0..59).toList().map { c -> String.format("%02d", c) }

    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
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

            FadingEdgeNumberPicker(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .background(
                        color = colorResource(id = R.color.light_grey),
                        shape = RoundedCornerShape(10.dp)
                    ),
                data = hours,
                selectedHeight = { userSelectedHeight = it },
            )
        }

        Text(
            modifier = Modifier
                .padding(top = 30.dp),
            text = ":",
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color = colorResource(id = R.color.light_grey)
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
            FadingEdgeNumberPicker(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .background(
                        color = colorResource(id = R.color.light_grey),
                        shape = RoundedCornerShape(10.dp)
                    ),
                data = mins,
                selectedHeight = { userSelectedHeight = it },
            )
        }
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FadingEdgeNumberPicker(
    modifier: Modifier = Modifier,
    data: List<String> = emptyList(),
    selectedHeight: (String) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    val leftRightFade = Brush.verticalGradient(
        0f to Color.Transparent,
        0.3f to Color.White,
        0.6f to Color.White,
        1f to Color.Transparent
    )

    LaunchedEffect(Unit) {
        lazyListState.scrollToItem(4)
    }

    LazyColumn(
        state = lazyListState,
        flingBehavior = snapBehavior,
        modifier = modifier
            .height(130.dp)
            /*.fadingEdge(brush = leftRightFade)*/,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(data) { index, item ->
            AnimatedText(
                lazyListState = lazyListState,
                index = index,
                item = item,
            ) {
                selectedHeight(it)
            }
        }
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
        //fontSize = if (isSelected) 32.sp else 24.sp,
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

private fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
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
    // CircularTimer(arrayListOf("0","1","2","3","4","5"))
}
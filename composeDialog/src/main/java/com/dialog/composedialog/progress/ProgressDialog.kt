package com.dialog.composedialog.progress

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.dialog.composedialog.R

@Composable
fun ProgressDialog(
    showDialog: Boolean = true,
    title: String,
    @DrawableRes dialogBackgroundColor: Int = R.color.white,
    @DrawableRes titleTextColor: Int = R.color.black,
    @DrawableRes progressBarColor: Int = R.color.black,
    @DrawableRes progressBarBackgroundColor: Int = R.color.grey,
    isCancelable: Boolean = true,
    isCircular: Boolean = false,
    isProgressEnabled: Boolean = true,
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
                        .background(color = colorResource(id = dialogBackgroundColor), RoundedCornerShape(20.dp))
                        .padding(top = 20.dp),
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
                            textAlign = TextAlign.Center
                        )
                        if (isProgressEnabled)
                            ProgressBarIndicator(
                                isCircular = isCircular,
                                progressBarColor = progressBarColor,
                                progressBarBackgroundColor = progressBarBackgroundColor
                            ) {
                                onDoneClick.invoke()
                            }
                        else
                            RegularProgressBar(
                                progressBarColor = progressBarColor,
                                progressBarBackgroundColor = progressBarBackgroundColor
                            )
                    }

                }

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProgressDialogPreview() {
    ProgressDialog(
        title = "Fetching data...",
        onDismiss = {
        },
        onDoneClick = {
        })
}
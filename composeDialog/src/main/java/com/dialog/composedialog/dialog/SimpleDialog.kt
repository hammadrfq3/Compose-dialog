package com.dialog.composedialog.dialog

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dialog.composedialog.R
import com.dialog.mydialog.ui.theme.MyDialogTheme

@Composable
fun SimpleDialog(
    title: String,
    positiveButtonText: String = "Ok",
    negativeButtonText: String = "Cancel",
    @DrawableRes dialogBackgroundColor: Int = R.color.white,
    @DrawableRes negativeButtonBackgroundColor: Int = R.color.white,
    @DrawableRes positiveButtonBackgroundColor: Int = R.color.dialog_black_btn,
    @DrawableRes positiveButtonTextColor: Int = R.color.white,
    @DrawableRes negativeButtonTextColor: Int = R.color.text_dark,
    @DrawableRes titleTextColor: Int = R.color.black,
    @DrawableRes descriptionTextColor: Int = R.color.light_grey,
    isCancelable: Boolean = true,
    textAlign: TextAlign = TextAlign.Center,
    description: String,
    onDismiss: () -> Unit,
    onDoneClick: () -> Unit,
) {


    MyDialog(
        title = title,
        negativeButtonText = negativeButtonText,
        positiveButtonText = positiveButtonText,
        isCancelable = isCancelable,
        description = description,
        textAlign = textAlign,
        positiveButtonTextColor = positiveButtonTextColor,
        negativeButtonTextColor = negativeButtonTextColor,
        dialogBackgroundColor = dialogBackgroundColor,
        negativeButtonBackgroundColor = negativeButtonBackgroundColor,
        positiveButtonBackgroundColor = positiveButtonBackgroundColor,
        titleTextColor = titleTextColor,
        descriptionTextColor = descriptionTextColor,
        onDismiss = {
            onDismiss.invoke()
        },
        onDoneClick = {
            onDoneClick.invoke()
        })

}


@Preview(showBackground = true)
@Composable
fun SimpleDialogPreview() {
    MyDialogTheme {
        SimpleDialog(
            title = "Simple Dialog",
            description = "A dialog is a type of modal window that appears in front of app content to provide critical information, or prompt for a decision to be made.",
            onDismiss = {

            }) {
        }
    }
}
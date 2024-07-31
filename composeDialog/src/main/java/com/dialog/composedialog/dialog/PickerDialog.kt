package com.dialog.composedialog.dialog

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dialog.composedialog.R
import com.dialog.mydialog.ui.theme.MyDialogTheme

@Composable
fun PickerDialog(
    title: String,
    positiveButtonText: String = "Ok",
    negativeButtonText: String = "Cancel",
    buttonShape: Shape = RoundedCornerShape(15.dp),
    dialogBackgroundColor: Color = colorResource(id = R.color.white),
    negativeButtonBackgroundColor: Color = colorResource(id = R.color.white),
    positiveButtonBackgroundColor: Color = colorResource(id = R.color.dialog_black_btn),
    positiveButtonTextColor: Color = colorResource(id = R.color.white),
    negativeButtonTextColor: Color = colorResource(id = R.color.text_dark),
    titleTextColor: Color = colorResource(id = R.color.black),
    descriptionTextColor: Color = colorResource(id = R.color.light_grey),
    isCancelable: Boolean = true,
    description: String,
    onDismiss: () -> Unit,
    onDoneClick: (String) -> Unit,
) {

    MyDialog(
        title = title,
        negativeButtonText = negativeButtonText,
        positiveButtonText = positiveButtonText,
        isCancelable = isCancelable,
        isPickerDialog = true,
        description = description,
        buttonShape = buttonShape,
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
            onDoneClick.invoke(it.toString())
        })

}


@Preview(showBackground = true)
@Composable
fun PickerDialogPreview() {
    MyDialogTheme {
        PickerDialog(
            title = "Picker Dialog",
            description = "A dialog is a type of modal window that appears in front of app content to provide critical information, or prompt for a decision to be made.",
            onDismiss = {

            }) {
        }
    }
}
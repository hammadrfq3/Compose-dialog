package com.dialog.composedialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dialog.composedialog.dialog.AnimatedDialog
import com.dialog.composedialog.dialog.EditableDialog
import com.dialog.composedialog.dialog.ImageDialog
import com.dialog.composedialog.dialog.PickerDialog
import com.dialog.composedialog.dialog.SimpleDialog
import com.dialog.composedialog.progress.ProgressDialog
import com.dialog.composedialog.ui.theme.ComposeDialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDialogTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RegularCircularProgressDialogButton()
        CircularProgressDialogButton()
        ProgressDialogButton()
        SimpleDialogButton()
        ImageDialogButton()
        EditableDialogButton()
        AnimatedDialogButton()
        PickerDialogButton()
    }
}

@Composable
fun RegularCircularProgressDialogButton() {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog)
        ProgressDialog(
            title = "Wait a moment",
            isCircular = true,
            isProgressEnabled = false,
            progressBarColor = R.color.orange,
            onDismiss = {
                showDialog = false
            }) {
            showDialog = false
        }

    Button(
        modifier = Modifier
            .padding(top = 15.dp)
            .height(45.dp),
        onClick = {
            showDialog = true
        },
        colors = ButtonColors(
            containerColor = colorResource(id = R.color.orange),
            contentColor = Color.White,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Text(
            text = "Circular Loading dialog",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }

}

@Composable
fun CircularProgressDialogButton() {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog)
        ProgressDialog(
            title = "Fetching data...",
            isCircular = true,
            onDismiss = {
                showDialog = false
            }) {
            showDialog = false
        }

    Button(
        modifier = Modifier
            .padding(top = 15.dp)
            .height(45.dp),
        onClick = {
            showDialog = true
        },
        colors = ButtonColors(
            containerColor = colorResource(id = R.color.orange),
            contentColor = Color.White,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Text(
            text = "Circular Progress dialog",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }

}

@Composable
fun ProgressDialogButton() {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog)
        ProgressDialog(
            title = "Fetching data...",
            progressBarColor = R.color.purple_700,
            onDismiss = {
                showDialog = false
            }) {
            showDialog = false
        }

    Button(
        modifier = Modifier
            .padding(top = 15.dp)
            .height(45.dp),
        onClick = {
            showDialog = true
        },
        colors = ButtonColors(
            containerColor = colorResource(id = R.color.orange),
            contentColor = Color.White,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Text(
            text = "Progress dialog",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }

}

@Composable
fun SimpleDialogButton() {

    var showImageDialog by remember { mutableStateOf(false) }

    if (showImageDialog)
        SimpleDialog(
            title = "Simple Dialog",
            description = "A dialog is a type of modal window that appears in front of app content to provide critical information, or prompt for a decision to be made.",
            textAlign = TextAlign.Start,
            onDismiss = {
                showImageDialog = false
            }) {
            showImageDialog = false
        }

    Button(
        modifier = Modifier
            .padding(top = 15.dp)
            .height(45.dp),
        onClick = {
            showImageDialog = true
        },
        colors = ButtonColors(
            containerColor = colorResource(id = R.color.orange),
            contentColor = Color.White,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Text(
            text = "Simple dialog",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }

}

@Composable
fun ImageDialogButton() {

    var showImageDialog by remember { mutableStateOf(false) }

    if (showImageDialog)
        ImageDialog(
            image = R.drawable.fire,
            title = "Image Dialog",
            description = "A dialog is a type of modal window that appears in front of app content to provide critical information, or prompt for a decision to be made.",
            onDismiss = {
                showImageDialog = false
            },
            onDoneClick = {
                showImageDialog = false
            })

    Button(
        modifier = Modifier
            .padding(top = 15.dp)
            .height(45.dp),
        onClick = {
            showImageDialog = true
        },
        colors = ButtonColors(
            containerColor = colorResource(id = R.color.orange),
            contentColor = Color.White,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Text(
            text = "Image dialog",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }

}

@Composable
fun EditableDialogButton() {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog)
        EditableDialog(
            title = "Editable Dialog",
            description = "A dialog is a type of modal window that appears in front of app content to provide critical information, or prompt for a decision to be made.",
            onDismiss = {
                showDialog = false

            }) {
            showDialog = false
        }

    Button(
        modifier = Modifier
            .padding(top = 15.dp)
            .height(45.dp),
        onClick = {
            showDialog = true
        },
        colors = ButtonColors(
            containerColor = colorResource(id = R.color.orange),
            contentColor = Color.White,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Text(
            text = "EditText dialog",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }

}

@Composable
fun AnimatedDialogButton() {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog)
        AnimatedDialog(
            title = "Animated Dialog",
            lottieAnimatorRes = R.raw.failure,
            description = "A dialog is a type of modal window that appears in front of app content to provide critical information, or prompt for a decision to be made." ,
            onDismiss = {
                showDialog = false
            }) {
            showDialog = false
        }

    Button(
        modifier = Modifier
            .padding(top = 15.dp)
            .height(45.dp),
        onClick = {
            showDialog = true
        },
        colors = ButtonColors(
            containerColor = colorResource(id = R.color.orange),
            contentColor = Color.White,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Text(
            text = "Animated dialog",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }

}

@Composable
fun PickerDialogButton() {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog)
        PickerDialog(
            title = "Picker Dialog",
            description = "A dialog is a type of modal window that appears in front of app content to provide critical information, or prompt for a decision to be made." ,
            onDismiss = {
                showDialog = false
            }) {
            showDialog = false
        }

    Button(
        modifier = Modifier
            .padding(top = 15.dp)
            .height(45.dp),
        onClick = {
            showDialog = true
        },
        colors = ButtonColors(
            containerColor = colorResource(id = R.color.orange),
            contentColor = Color.White,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Text(
            text = "Picker dialog",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeDialogTheme {
        MainScreen()
    }
}
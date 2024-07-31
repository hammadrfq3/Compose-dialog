package com.dialog.composedialog.progress

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.dialog.composedialog.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProgressBarIndicator(
    isCircular: Boolean = false,
    progressBarColor: Color = colorResource(id = R.color.black),
    progressBarBackgroundColor: Color = colorResource(id = R.color.grey),
    onDoneClick: () -> Unit,
) {
    var currentProgress by remember { mutableStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope() // Create a coroutine scope

    LaunchedEffect(Unit) {
        scope.launch {
            loading = true
            loadProgress { progress ->
                currentProgress = progress
            }
            loading = false // Reset loading when the coroutine finishes
            onDoneClick.invoke()
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 35.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        if (loading) {
            if (isCircular) {
                CircularProgressIndicator(
                    progress = { currentProgress },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    color = progressBarColor,
                    trackColor = progressBarBackgroundColor
                )
            } else
                LinearProgressIndicator(
                    progress = { currentProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    color = progressBarColor,
                    trackColor = progressBarBackgroundColor
                )
        }
    }
}

@Composable
fun RegularProgressBar(
    progressBarColor: Color = colorResource(id = R.color.black),
    progressBarBackgroundColor: Color = colorResource(id = R.color.grey),
) {

    val loading by remember { mutableStateOf(true) }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 35.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        if (loading)
            CircularProgressIndicator(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(16.dp)),
                color = progressBarColor,
                trackColor = progressBarBackgroundColor
            )
    }

}

/** Iterate the progress value */
suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(100)
    }
}
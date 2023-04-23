package io.behzod.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.behzod.features.AddButton

@Composable
fun Note(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoteTop(folder = { }) {

        }
        Spacer(modifier = modifier.weight(1f))
        AddButton() {}
        Spacer(modifier = modifier.height(15.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun NotePreview() {
    Note()
}

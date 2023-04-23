package io.behzod.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthenticationButton(
    modifier: Modifier = Modifier,
    enableAuthentication: Boolean,
    onNextClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Blue,
            contentColor = colorResource(id = io.behzod.features.R.color.white)
        ),
        enabled = enableAuthentication,
        shape = RoundedCornerShape(12.dp),
        onClick = { onNextClick() }) {
        Text(
            text = stringResource(id = io.behzod.features.R.string.next),
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
fun AuthenticationButtonPreview() {
    AuthenticationButton(enableAuthentication = false) {

    }
}

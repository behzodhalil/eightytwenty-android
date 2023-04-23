package io.behzod.features

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .width(80.dp)
            .height(60.dp),
        shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp, bottomStart = 36.dp, bottomEnd = 36.dp),
        onClick = { onClick() }) {
        Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = null)
    }
}

@Preview
@Composable
fun AddButtonPreview() {
    AddButton() {

    }
}

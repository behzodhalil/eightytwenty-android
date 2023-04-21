package io.behzod.ui

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun NameInput(
    modifier: Modifier = Modifier,
    name: String?,
    onNameChanged: (name: String) -> Unit,
    onNextClick: () -> Unit
) {
    TextField(
        modifier = modifier,
        value = name ?: "",
        onValueChange = { currentName ->
            onNameChanged(currentName)
        },
        singleLine = true,
        label = {
            Text(text = stringResource(id = io.behzod.features.R.string.name))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                onNextClick()
            }
        )
    )
}

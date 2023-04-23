package io.behzod.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun AuthenticationTitle(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
) {
    Text(
        text = stringResource(
            id =
            if (authenticationMode == AuthenticationMode.SIGN_IN) {
                io.behzod.features.R.string.sign_in_to_account
            } else {
                io.behzod.features.R.string.sign_up_to_account
            }
        ),
        color = colorResource(id = io.behzod.features.R.color.black),
        fontSize = 20.sp
    )
}

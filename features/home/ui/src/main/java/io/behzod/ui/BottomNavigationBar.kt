package io.behzod.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val items = listOf(
        BottomNavigationItem.Productivity,
        BottomNavigationItem.Task,
        BottomNavigationItem.Note,
        BottomNavigationItem.Habit,
        BottomNavigationItem.Reminder
    )

    BottomNavigation(
        backgroundColor = colorResource(id = io.behzod.features.R.color.solitude),
        contentColor = Color.White
    ) {
        val currentRoute = currentRoute(navController = navController)
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = null) },
                label = { Text(fontSize = 8.sp, softWrap = false, text = stringResource(id = item.title)) },
                selectedContentColor = Color.Blue,
                unselectedContentColor = Color.Black.copy(alpha = 0.7f),
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                    }
                })
        }
    }
}

@Composable
private fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

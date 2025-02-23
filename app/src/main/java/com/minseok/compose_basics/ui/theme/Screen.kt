package com.minseok.compose_basics.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector, val title: String) {
    object Home : Screen("home", Icons.Default.Home, "홈")
    object Profile : Screen("profile", Icons.Default.Person, "프로필")
    object Settings : Screen("settings", Icons.Default.Settings, "설정")
}


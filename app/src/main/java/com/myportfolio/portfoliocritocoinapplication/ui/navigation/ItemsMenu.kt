package com.myportfolio.portfoliocritocoinapplication.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Logout
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemsMenu (

    val icon: ImageVector,
    val title: String,

){
    data object Item1: ItemsMenu(
        Icons.AutoMirrored.Filled.Logout,
        "LogOut"
    )
}
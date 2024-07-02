package com.myportfolio.portfoliocritocoinapplication.ui.views.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.LoginViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.SingUpViewModel
import com.myportfolio.portfoliocritocoinapplication.util.ConstantsColors.Companion.CUSTOM_GREEN_BANNER

@Composable
fun TabsView(navController: NavController,
             loginViewModel: LoginViewModel,
             signUpViewModel: SingUpViewModel
) {

    var selectedTab by remember { mutableStateOf(0) }
    var tabs = listOf("Log In", "Sign In")

    Column {
        TabRow(
            selectedTabIndex = selectedTab,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTab])
                )
            }////indicator
        ) {
            tabs.forEachIndexed { index, title ->

                Tab(selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(text = title, color = Color.White) },
                    modifier = Modifier.background(Color(CUSTOM_GREEN_BANNER))
                ) ///Tab
            }////for eachIndexed
        }////TabRow

        when (selectedTab){

            0 -> LoginView(navController,loginViewModel)
            1 -> RegisterView(navController,signUpViewModel )
        }

    }///column
}/////////////tabsview

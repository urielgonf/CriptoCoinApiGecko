package com.myportfolio.portfoliocritocoinapplication.ui.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.myportfolio.portfoliocritocoinapplication.R
import com.myportfolio.portfoliocritocoinapplication.ui.navigation.ItemsMenu
import com.myportfolio.portfoliocritocoinapplication.ui.navigation.Screens
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.CoinsByIdViewModel
import com.myportfolio.portfoliocritocoinapplication.util.ConstantsColors.Companion.CUSTOM_BLACK
import com.myportfolio.portfoliocritocoinapplication.util.ConstantsColors.Companion.CUSTOM_GREEN_BANNER
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    showMenuButton:Boolean,
    showBackButton: Boolean,
    showSearchButton: Boolean,
    onClickBackButton: () -> Unit,
    onClickSearchButton: () -> Unit,
    onClickMenuhButton: () -> Unit,
) {
    TopAppBar(
        title = {
            BannerTop()
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(CUSTOM_GREEN_BANNER)
        ),
        navigationIcon = {

                Row {
                    if(showMenuButton){
                        IconButton(onClick = onClickMenuhButton) {
                            Column {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = null,
                                    tint = Color.White,
                                )
                                Text(text = "Menu",
                                    color = Color.White,
                                    fontSize = 8.sp
                                )
                            }

                        }
                    }
                    else{
                        Spacer(modifier = Modifier.width(48.dp))
                    }


                }

        },
        actions = {

            if(!showSearchButton && !showBackButton){
                Spacer(modifier = Modifier.width(48.dp) )
            }
            if(showSearchButton){
                IconButton(
                    onClick =  onClickSearchButton
                ) {
                    Column {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Text(text = "Search",
                            color = Color.White,
                            fontSize = 8.sp
                        )
                    }

                }
            }
            if (showBackButton) {
                IconButton(onClick = onClickBackButton) {
                    Column {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White,
                        )
                        Text(
                            text = "Back",
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }
                }

            }




        }
    )
}

@Composable
fun BannerTop() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.coingecko),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(50.dp)
                .padding(horizontal = 30.dp)
        )
    }
}

@Composable
fun MainBottomBar(navController: NavController) {
    val items = listOf(
        NavigationItem("Home", Icons.Default.Home, Screens.HomeScreen.route, description = "Home"),
        NavigationItem("List", Icons.AutoMirrored.Filled.List, Screens.CoinListScreen.route, description = "List"),
        NavigationItem("Favourites", Icons.Default.Favorite, Screens.FavoritesScreen.route, description = "Favourites"),
        NavigationItem("Room", Icons.Default.Favorite, Screens.FavoritesScreenRoom.route, description = "Room"),
        NavigationItem("News", Icons.Default.Newspaper, Screens.NewsScreen.route, description = "News")
    )
    NavigationBar(containerColor = Color(CUSTOM_GREEN_BANNER)) {
        items.forEach { item ->
            NavigationBarItem(
                label = { Text(text = item.label, color = Color.White) },
                selected = false,
                onClick = { navController.navigate(item.route) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.description, tint = Color.White) }
            )
        }
    }
}

data class NavigationItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
    val description: String
)

@Composable
fun MenuLateral(
    drawerState: DrawerState,
    onClickLogOutButton: () -> Unit,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val customBackgroundColor = Color(CUSTOM_BLACK) // Cambia esto al color que desees

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(200.dp)
                    .background(customBackgroundColor) // Aplicar color de fondo personalizado
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(customBackgroundColor) // Asegurar que toda la columna tenga el color de fondo
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    NavigationDrawerItem(
                        icon = { Icon(ItemsMenu.Item1.icon, contentDescription = null, tint = Color.White) },
                        label = { Text(text = ItemsMenu.Item1.title, color = Color.White, fontSize = 14.sp) },
                        selected = false,
                        onClick = { onClickLogOutButton()
                                    scope.launch { drawerState.close() } },
                        modifier = Modifier.background(customBackgroundColor) // Aplicar color de fondo a cada item
                    )
                }
            }
        }
    ) {
        content()
    }
}
data class MenuDropDownItem(val text: String, val time: Int?)

@Composable
fun DropDownMenu(expanded: Boolean, onDismissRequest: () -> Unit,viewModel: CoinsByIdViewModel) {
    val context = LocalContext.current
    val items = listOf(
        MenuDropDownItem(text = "No notificatios",null),
        MenuDropDownItem(text = "15min", time = 15),
        MenuDropDownItem(text = "20min", time =20),
        MenuDropDownItem(text = "30min", time =30),
        MenuDropDownItem(text = "45min", time =45),
        MenuDropDownItem(text = "60min", time =60),
        MenuDropDownItem(text = "180min", time =180),
    )
    Column(modifier = Modifier.background(Color(CUSTOM_BLACK))) {

    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        properties = PopupProperties(focusable = false)
    ) {
        items.forEach { item ->

            DropdownMenuItem(
                text = { Text(text = item.text, color = Color.White, fontSize = 16.sp) },
                onClick = {
                    viewModel.setIntervalMinutes(item.time)
                    onDismissRequest()
                    // Ejemplo de acción, como mostrar un Toast
                    Toast.makeText(context, "${item.text} selected", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

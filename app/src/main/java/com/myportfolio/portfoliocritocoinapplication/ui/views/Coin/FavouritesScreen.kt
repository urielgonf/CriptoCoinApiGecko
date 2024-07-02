package com.myportfolio.portfoliocritocoinapplication.ui.views.Coin

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.FavouritesModel
import com.myportfolio.portfoliocritocoinapplication.ui.navigation.Screens
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.FavouritesViewModel
import com.myportfolio.portfoliocritocoinapplication.util.ConstantsColors
import com.myportfolio.portfoliocritocoinapplication.util.ConstantsColors.Companion.CIRCLE_GREEN

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavouritesScreen(
    navController: NavHostController,
    favouritesViewModel: FavouritesViewModel
) {
    LaunchedEffect(Unit) {
        favouritesViewModel.fetchFavourites()
    }

    val favouritesCoins by favouritesViewModel.facoins.collectAsState(emptyList())

    ContentFavouritesScreen(favouritesCoins, navController)
}

@Composable
fun ContentFavouritesScreen(favouritesCoins: List<FavouritesModel>, navController: NavHostController) {
    if (favouritesCoins.isNotEmpty()) {

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
            ) {
                items(favouritesCoins, key = { it.id }) { coin ->
                    CoinFavouriteItem(coin) {
                        navController.navigate(Screens.DetailCoinScreen.createRoute(coin.id))
                    }
                }

        }
    } else {
        EmptyFavouritesMessage()
    }
}

@Composable
fun EmptyFavouritesMessage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Here you will find your favourite coins")
    }
}

@Composable
fun CoinFavouriteItem(coin: FavouritesModel, onClick: () -> Unit) {
    val image = rememberAsyncImagePainter(model = coin.thumbImage)
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(
                color = Color(ConstantsColors.ROW_BACKGROUND_COLOR),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        CoinThumbnail(image)
        Spacer(modifier = Modifier.width(16.dp))
        CoinDetails(coin)
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun CoinThumbnail(image: Painter) {
    Surface(
        color = Color(CIRCLE_GREEN),
        shape = CircleShape,
        modifier = Modifier
            .size(80.dp)
            .padding(8.dp),
        shadowElevation = 8.dp
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
                .background(Color(CIRCLE_GREEN))
        ) {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
fun CoinDetails(coin: FavouritesModel) {
    Column {
        Text(
            text = coin.name,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = coin.id,
            color = Color(ConstantsColors.ROW_BACKGROUND_COLOR2),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

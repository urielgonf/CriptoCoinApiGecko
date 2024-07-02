package com.myportfolio.portfoliocritocoinapplication.ui.views.Coin

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.CoinDetailModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.CoinsByIdViewModel
import com.myportfolio.portfoliocritocoinapplication.ui.views.DropDownMenu
import com.myportfolio.portfoliocritocoinapplication.util.ConstantsColors
import com.myportfolio.portfoliocritocoinapplication.util.ConstantsColors.Companion.ROW_BACKGROUND_COLOR
import java.math.BigDecimal
import kotlin.math.abs
import kotlin.math.absoluteValue


@Composable
fun CoinDetailScreen(
    viewModel: CoinsByIdViewModel,
    id: String,
) {



    LaunchedEffect(key1 = id) {
        viewModel.getCoinById(id)
        viewModel.fetchFavourites()
    }

    val coin by viewModel.coin.observeAsState()
    val isFavourite = viewModel.favourites.observeAsState(emptyList()).value.any { it.id == id }

    DisposableEffect(Unit) {
        onDispose {
            // Desvincula los observadores de LiveData aquí
            viewModel.clearCoinObserver()
        }
    }
    ContentDetailView(coin, PaddingValues(), viewModel,isFavourite)

}

@Composable
fun ContentDetailView(coin: CoinDetailModel?, pad: PaddingValues,viewModel: CoinsByIdViewModel,isFavourite:Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(pad),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        coin?.let {

            CoinName(it.name,it.id,it.image?.large?:"N/A", it.marketData?.currentPrice?.usd?:0.000,viewModel,isFavourite)
            CoinImage(it.image?.large?:"N/A")
            PriceInDollars(it.marketData?.currentPrice?.usd?:0.000,it.id,isFavourite,viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                PriceChange(abs(it.marketData?.price_change_percentage_24h?: 0.000))
                HashingAlgorithm(it.hashingAlgorithm?:"N/A")
            }
            Spacer(modifier = Modifier.height(32.dp))
            TextDescription(it.description?.en?:"No description")
        } ?: run {
            // Mostrar un indicador de carga o mensaje de error si `coin` es nulo
            Text(text = "Loading...", color = Color.White)
        }
    }
}
@Composable
fun TextDescription(en: String) {
    val scroll = rememberScrollState()
    Text(text = "Description:",
        color = Color.White,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = en,
        color = Color.White,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        textAlign = TextAlign.Justify,
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
            .verticalScroll(scroll)
    )
}

@Composable
fun HashingAlgorithm(hashingAlgorithm: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hashing Algorithm",
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.White,)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = hashingAlgorithm,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            color = Color.White,
            )
    }



}

@Composable
fun PriceChange(priceChange:Double,) {



    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Price Change for 24h",
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.White,)
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "${String.format("%.3f" ,priceChange)} %",
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            color = Color.White,)
    }

}
fun contarCerosDespuesDelPunto(numero: Double): Int {
    val bigDecimal = BigDecimal.valueOf(numero).stripTrailingZeros()
    val numeroStr = bigDecimal.toPlainString()
    // Verificar si hay un punto decimal en la cadena
    if (!numeroStr.contains('.')) {
        return 0 // No hay parte decimal
    }

    // Obtener la parte decimal de la cadena
    val parteDecimal = numeroStr.substringAfter('.')

    // Contar los ceros al final de la parte decimal
    var contadorCeros = 0
    for (i in parteDecimal.indices) {
        if (parteDecimal[i] == '0') {
            contadorCeros++
        } else {
            break
        }
    }
    return contadorCeros
}
@Composable
fun PriceInDollars(currentPrice: Double,id: String,isFavourite: Boolean,viewModel: CoinsByIdViewModel) {
    if(isFavourite){
        Log.d("favouritecoin","entre")
        viewModel.updatePrice(id,currentPrice.toString(),{},{})
    }
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(
                color = Color(ROW_BACKGROUND_COLOR), shape = RoundedCornerShape(16.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        val tCeros = contarCerosDespuesDelPunto(currentPrice)

        val finalPrince =if(tCeros >= 1){
            String.format("%.${tCeros+3}f", currentPrice)
        }else{
            String.format("%.2f", currentPrice)
        }



        Text(
            text = "${finalPrince} USD",
            fontWeight = FontWeight.Medium,
            fontSize = 35.sp,
            color = Color.White,
        )
    }
}

@Composable
fun CoinImage(large: String) {
    val image = rememberAsyncImagePainter(model = large)
    Surface(
        color = Color(ConstantsColors.CIRCLE_GREEN),
        shape = CircleShape,
        modifier = Modifier
            .size(150.dp)
            .padding(8.dp),
        shadowElevation = 12.dp,


        ) {

        Image(painter = image,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(100.dp))
        )
    }
}

@Composable
fun CoinName(name: String, id: String, thumbImage: String, currentValue: Double, viewModel: CoinsByIdViewModel,isFavourite: Boolean) {

    val context = LocalContext.current
    ////////paint red favurite///////////
    //////////////////////////
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(
                color = Color(ROW_BACKGROUND_COLOR), shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth()
    ) {
        if (isFavourite) {

            IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.NotificationsActive,
                        tint = Color.White,
                        contentDescription = "",
                    )
            }
            DropDownMenu(expanded = expanded, onDismissRequest = { expanded = false })
        }


        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
        IconButton(
            onClick = {
                viewModel.toggleFavourite(id, name, thumbImage, currentValue.toString(), {
                    if (isFavourite) {
                        Toast.makeText(
                            context,
                            "${name} deleted from favourites",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "${name} added to favourites",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                    {

                    }
                )
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                tint = if (isFavourite) Color.Red else Color.White,
                contentDescription = "Like"
            )
        }
    }
}
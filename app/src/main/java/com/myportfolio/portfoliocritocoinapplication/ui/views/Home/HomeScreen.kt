package com.myportfolio.portfoliocritocoinapplication.ui.views.Home

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.HomeViewModel
import com.myportfolio.portfoliocritocoinapplication.util.ConstantsColors.Companion.CIRCLE_GREEN
import com.myportfolio.portfoliocritocoinapplication.util.ConstantsColors.Companion.CUSTOM_GREEN_BANNER

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val name by homeViewModel.userName.observeAsState()

    LaunchedEffect(Unit) {
        homeViewModel.fetchUserName()
    }

    name?.let {
        ContentHomeScreen(it)
    }
}

@Composable
fun ContentHomeScreen(name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(CUSTOM_GREEN_BANNER))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextUserName(name)
        Spacer(modifier = Modifier.height(80.dp))
        GifImage(gifName = "gecko1")
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun TextUserName(name: String) {
    val welcomeText = "Welcome $name"
    Text(
        text = welcomeText,
        color = Color.White,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 32.sp,
    )
}

@SuppressLint("DiscouragedApi")
@Composable
fun GifImage(
    gifName: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

    val resourceId = remember {
        context.resources.getIdentifier(gifName, "drawable", context.packageName)
    }

    Box(
        modifier = modifier.size(200.dp) // Ajusta el tamaño aquí según sea necesario
    ) {
        Surface(
            color = Color.Transparent,
            shape = CircleShape,
            border = BorderStroke(4.dp, Color(CIRCLE_GREEN)), // Color y ancho del borde
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context)
                        .data(data = resourceId)
                        .apply {
                            size(200, 200) // Cambia el tamaño aquí según sea necesario
                        }
                        .build(),
                    imageLoader = imageLoader
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape) // Recorte circular
            )
        }
    }
}


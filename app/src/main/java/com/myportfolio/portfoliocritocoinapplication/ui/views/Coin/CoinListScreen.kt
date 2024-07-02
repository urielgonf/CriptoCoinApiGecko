package com.myportfolio.portfoliocritocoinapplication.ui.views.Coin

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myportfolio.portfoliocritocoinapplication.data.Coins.model.CoinModel
import com.myportfolio.portfoliocritocoinapplication.ui.navigation.Screens
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.CoinsListViewModel
import com.myportfolio.portfoliocritocoinapplication.util.ConstantsColors.Companion.CIRCLE_GREEN
import com.myportfolio.portfoliocritocoinapplication.util.ConstantsColors.Companion.ROW_BACKGROUND_COLOR
import com.myportfolio.portfoliocritocoinapplication.util.ConstantsColors.Companion.ROW_BACKGROUND_COLOR2


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CoinListScreen(
    viewModel: CoinsListViewModel,
    navController: NavController,
    isSearchFieldVisible: Boolean
) {
    val coins by viewModel.coins.observeAsState()
    var search by rememberSaveable { mutableStateOf("") }

    ContentListScreen(
        navController,
        coins,
        PaddingValues(),
        search,
        isSearchFieldVisible
    ) { searchTerm ->
        search = searchTerm
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentListScreen(
    navController: NavController,
    coins: List<CoinModel>?,
    pad: PaddingValues,
    search: String,
    isSearchFieldVisible: Boolean,
    onSearchTermChanged: (String) -> Unit
) {
    val filteredCoins = remember(coins, search) {
        coins?.filter { it.name.contains(search, ignoreCase = true) }
    }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(pad)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isSearchFieldVisible) {
            SearchField(
                search = search,
                onSearchTermChanged = onSearchTermChanged,
                focusRequester = focusRequester,
                focusManager = focusManager
            )
        }

        AnimatedVisibility(
            visible = true,
            enter = slideInHorizontally(initialOffsetX = { it }) + fadeIn(),
            exit = slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
            ) {
                items(filteredCoins.orEmpty(), key = { it.id }) { coin ->
                    CoinItem(coin = coin) {
                        Log.d("coinsssss", "${coin.id}")
                        navController.navigate(Screens.DetailCoinScreen.createRoute(coin.id))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    search: String,
    onSearchTermChanged: (String) -> Unit,
    focusRequester: FocusRequester,
    focusManager: FocusManager
) {
    TextField(
        value = search,
        onValueChange = onSearchTermChanged,
        label = { Text(text = "Search") },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .padding(horizontal = 16.dp, vertical = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color.White,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        textStyle = TextStyle(color = Color.White)
    )
}

@Composable
fun CoinItem(coin: CoinModel, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(
                color = Color(ROW_BACKGROUND_COLOR),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            CoinIcon(symbol = coin.symbol)
            CoinDetails(name = coin.name, id = coin.id)
        }
    }
}

@Composable
fun CoinIcon(symbol: String) {
    Surface(
        color = Color(CIRCLE_GREEN),
        shape = CircleShape,
        modifier = Modifier
            .size(80.dp)
            .padding(8.dp),
        shadowElevation = 20.dp
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = symbol,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun CoinDetails(name: String, id: String) {
    Column {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = id,
            color = Color(ROW_BACKGROUND_COLOR2),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}


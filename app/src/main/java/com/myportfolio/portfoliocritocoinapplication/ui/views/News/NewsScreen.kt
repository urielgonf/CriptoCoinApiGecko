package com.myportfolio.portfoliocritocoinapplication.ui.views.News

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.myportfolio.portfoliocritocoinapplication.data.News.model.NewsModel
import com.myportfolio.portfoliocritocoinapplication.ui.viewmodel.NewsViewModel
import com.myportfolio.portfoliocritocoinapplication.util.ConstantsColors.Companion.ROW_BACKGROUND_COLOR
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsScreen(
    viewModel: NewsViewModel
) {
    val news by viewModel.news.observeAsState()
    ContentNewsScreen(news)
}

@Composable
fun ContentNewsScreen(news: List<NewsModel>?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "NEWS",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 48.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        news?.let { newsList ->
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
            ) {
                items(newsList, key = { it.id }) { item ->
                    if (item.description.isNotEmpty() && item.image != "None") {
                        NewsItem(news = item)
                    }
                }
            }
        } ?: Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No news available", color = Color.Gray)
        }
    }
}

@Composable
fun NewsItem(news: NewsModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(ROW_BACKGROUND_COLOR), RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainName(news.title)
        Spacer(modifier = Modifier.height(8.dp))
        if (news.image != "None") {
            NewsImage(news.image)
        }
        ShowWebSite(news.url)
        Spacer(modifier = Modifier.height(8.dp))
        NewsDescription(news.description)
        Spacer(modifier = Modifier.height(8.dp))
        Author(news.author, news.published)
    }
}

@Composable
fun ShowWebSite(website: String) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(website)) }
    Button(
        onClick = { context.startActivity(intent) },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color.Gray
        )
    ) {
        Text(text = "CHECK IT")
    }
}

@Composable
fun Author(author: String, published: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val date = published.substring(0, 16)
        Text(
            text = author,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = date,
            color = Color.White,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    }
}

@Composable
fun NewsDescription(description: String) {
    Text(
        text = description,
        color = Color.White,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Justify,
        fontSize = 16.sp,
        modifier = Modifier
            .padding(15.dp)
    )
}

@Composable
fun NewsImage(image: String) {
    val imageNews = rememberAsyncImagePainter(model = image)
    Surface(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp),
        shadowElevation = 12.dp
    ) {
        Image(
            painter = imageNews,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun MainName(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(ROW_BACKGROUND_COLOR), RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .padding(8.dp)
    )
}


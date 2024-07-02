package com.myportfolio.portfoliocritocoinapplication.Workers

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.myportfolio.portfoliocritocoinapplication.R
import kotlin.random.Random
class NotificationService(private val context: Context) {

    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    fun showBasicNotification(favouriteCoin:String,coinPrice: String) {
        val notification = NotificationCompat.Builder(context, "1")
            .setContentTitle("New Price of Your Favourite Coin")
            .setContentText("The current price of $favouriteCoin is $coinPrice USD.")
            .setSmallIcon(R.drawable.gecko1)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(
            Random.nextInt(),
            notification
        )

    }
}
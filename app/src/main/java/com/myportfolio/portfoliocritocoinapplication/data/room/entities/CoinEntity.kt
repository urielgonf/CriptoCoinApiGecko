package com.myportfolio.portfoliocritocoinapplication.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class CoinEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val currentPrice: String,
)
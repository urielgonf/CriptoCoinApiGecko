package com.myportfolio.portfoliocritocoinapplication.data.Coins.model

import com.google.gson.annotations.SerializedName

data class FavouritesModel(

    @SerializedName("email")   val email: String="",
    @SerializedName("id")      val id: String="",
    @SerializedName("name")    val name : String="",
    @SerializedName("thumbImage") val thumbImage:String= "",
    @SerializedName("currentValue") var currentValue:String= "",
    @SerializedName("idDoc")   val idDoc: String = ""

)
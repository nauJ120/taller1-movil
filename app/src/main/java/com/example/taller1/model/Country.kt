package com.example.taller1.model

import kotlinx.serialization.Serializable
import androidx.recyclerview.widget.RecyclerView

@Serializable

data class Country(
    val image:String,
    val name:String,
    val nativeName:String,
    val id:String,
    val moneyName:String,
    val moneySymbol:String
)

@Serializable
data class CountryList(
    val Countries: List<Country>
)
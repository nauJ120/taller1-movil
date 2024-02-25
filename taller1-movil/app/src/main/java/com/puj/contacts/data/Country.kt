package com.puj.contacts.data


data class Country(
    val countryName: String,
    val alpha2Code: String,
    val alpha3Code: String,
    val nativeName: String,
    val region: String,
    val subRegion: String,
    val latitude: String,
    val longitude: String,
    val area: Int,
    val numericCode: Int,
    val nativeLanguage: String,
    val currencyCode: String,
    val currencyName: String,
    val currencySymbol: String,
    val flag: String,
    val flagPng: String
)

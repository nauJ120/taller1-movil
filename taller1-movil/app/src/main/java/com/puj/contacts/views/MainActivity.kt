package com.puj.contacts.views

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.puj.contacts.R
import com.puj.contacts.adapter.CountryAdapter
import com.puj.contacts.data.Country
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val countryListView = findViewById<ListView>(R.id.countryListView)
        val phoneFab :FloatingActionButton = findViewById(R.id.phoneFab)
        val jsonString = InputStreamReader(resources.openRawResource(R.raw.countries)).readText()
        val countryListType = object : TypeToken<List<Country>>() {}.type
        val countries: List<Country> = Gson().fromJson(jsonString, countryListType)

        val adapter = CountryAdapter(this, R.layout.country_card, countries)
        countryListView.adapter = adapter

        phoneFab.setOnClickListener {
            // Implementar la acción al hacer clic en el botón de teléfono
            // Puedes agregar código para realizar una llamada o cualquier otra acción deseada.
        }
    }
}

package com.puj.contacts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.puj.contacts.data.Country

import com.bumptech.glide.Glide
import com.puj.contacts.R

class CountryAdapter(context: Context, resource: Int, objects: List<Country>) :
    ArrayAdapter<Country>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val country = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.country_card, parent, false)

        // Check if the country and flag URL are not null
        if (country != null && !country.flag.isNullOrBlank()) {
            view.findViewById<TextView>(R.id.countryName).text = country?.countryName
            view.findViewById<TextView>(R.id.nativeName).text = country?.nativeName
            view.findViewById<TextView>(R.id.alpha3Code).text = country?.alpha3Code
            view.findViewById<TextView>(R.id.currency).text = "${country?.currencyName} (${country?.currencySymbol})"
            Glide.with(context)
                .load(country.flag)
                .into(view.findViewById<ImageView>(R.id.flag))
        }
        else
        {
            view.findViewById<TextView>(R.id.countryName).text =
                context.getString(R.string.no_name_found)
            view.findViewById<TextView>(R.id.nativeName).text = context.getString(R.string.no_name_found)
            view.findViewById<TextView>(R.id.alpha3Code).text =
                context.getString(R.string.no_alpha_code_found)
            view.findViewById<ImageView>(R.id.flag).setImageResource(R.drawable.placeholder_image)
        }

        return view
    }
}

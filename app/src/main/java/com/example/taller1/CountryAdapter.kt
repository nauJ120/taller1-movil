package com.example.taller1
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taller1.model.Country

class CountryAdapter(private val countries: List<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val nativeNameTextView: TextView = itemView.findViewById(R.id.nativeName)
        private val idCountry: TextView = itemView.findViewById(R.id.id)
        private val symbolMoney: TextView = itemView.findViewById(R.id.MoneySymbol)
        private val moneyName: TextView = itemView.findViewById(R.id.MoneyName)
        private val imageView: ImageView = itemView.findViewById(R.id.flagImage)

        fun bind(country: Country) {
            nameTextView.text = "${country.name}"
            nativeNameTextView.text = "${country.nativeName}"
            idCountry.text = "${country.id}"
            symbolMoney.text = "${country.moneySymbol}"
            moneyName.text = "${country.moneyName}"
        }
    }

}
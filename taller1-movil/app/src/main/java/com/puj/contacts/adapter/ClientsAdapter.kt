package com.puj.contacts.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.puj.contacts.data.Client
import com.puj.contacts.databinding.AdapterContactsBinding

class ClientsAdapter(context: Context, clients: List<Client>)
    : ArrayAdapter<Client>(context, 0, clients) {
    private val clnts: MutableList<Client> = ArrayList(clients)

    override fun getCount() = clnts.size

    override fun getItem(pos: Int) = clnts[pos]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var binding: AdapterContactsBinding = if(convertView == null)
            AdapterContactsBinding.inflate(LayoutInflater.from(context), parent, false)
        else
            AdapterContactsBinding.bind(convertView)
        binding.contactName.text = clnts[position].name
        binding.contactEmail.text = clnts[position].email
        binding.buttonPhone.text = clnts[position].phone
        if(clnts[position].photo.isNotEmpty())
            binding.contactsPhoto.setImageURI(Uri.parse(clnts[position].photo))
        else
            binding.contactsPhoto.visibility = View.GONE
        return binding.root
    }
}
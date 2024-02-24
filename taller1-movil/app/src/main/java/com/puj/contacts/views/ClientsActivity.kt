package com.puj.contacts.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puj.contacts.R
import com.puj.contacts.adapter.ClientsAdapter
import com.puj.contacts.data.Client
import com.puj.contacts.databinding.ActivityClientsBinding

class ClientsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClientsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var clients: List<Client> = listOf(
            Client("Juan", "a@a.com","1234567890", ""),
            Client("Pedro", "b@b.com","1234567890", ""),
            Client("Maria", "c@c.com","1234567890", ""),)

        binding.clientsList.adapter = ClientsAdapter(this, clients)

    }
}
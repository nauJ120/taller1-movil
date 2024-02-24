package com.puj.contacts.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puj.contacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonContacts.setOnClickListener{
            startActivity(Intent(this, ContactsActivity::class.java))
        }
        binding.buttonClients.setOnClickListener{
            startActivity(Intent(this, ClientsActivity::class.java))
        }

    }
}
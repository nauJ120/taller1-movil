package com.puj.contacts.views

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.content.ContextCompat
import com.puj.contacts.R
import com.puj.contacts.adapter.ContactsAdapter
import com.puj.contacts.databinding.ActivityContactsBinding
import com.puj.contacts.utils.Alerts

class ContactsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding
    private val alerts: Alerts = Alerts()
    private val PERM_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED -> {
//                alerts.longToast(this, "Ya Tengo permisos 😁")
                getContacts()
            }

            shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                alerts.indefiniteSnackbar(binding.root, "El permiso de contactos es necesario 😭")
            }

            else -> {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    PERM_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERM_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //El usuario acepto el permiso :)
                    alerts.longSimpleSnackbar(
                        binding.root,
                        "Gracias por aceptar el permiso de contactos 😊"
                    )
                    getContacts()
                } else {
                    //El usuario rechazo el permiso :(
                    alerts.indefiniteSnackbar(binding.root, "No puedo acceder a tus contactos 😢")
                }
            }
        }
    }

    fun getContacts() {
        val cursor: Cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            ContactsAdapter.PROJECTION,
            ContactsAdapter.FILTER,
            null,
            ContactsAdapter.ORDER
        )!!
        binding.contactsList.adapter = ContactsAdapter(this, cursor)
    }
}
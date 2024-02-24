package com.puj.contacts.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.cursoradapter.widget.CursorAdapter
import com.puj.contacts.databinding.AdapterContactsBinding

class ContactsAdapter(context: Context?, cursor: Cursor?): CursorAdapter(context, cursor, 0){
    companion object{
        //Podemos definir constantes para el adapter como
        val PROJECTION = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.STARRED,
            ContactsContract.Contacts.PHOTO_URI
        )

        val FILTER = "${ContactsContract.Contacts.DISPLAY_NAME} NOT LIKE '%@%'"

        val ORDER = "${ContactsContract.Contacts.DISPLAY_NAME} COLLATE NOCASE"
    }

    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        //Incializamos el binding
        val binding: AdapterContactsBinding =
            AdapterContactsBinding.inflate(
                LayoutInflater.from(context), parent, false)
        return binding.root
    }

    @SuppressLint("Range")
    override fun bindView(view: View, context: Context, cursor: Cursor) {
        val binding = AdapterContactsBinding.bind(view!!)
        //Podemos usar el binding para acceder a los elementos del layout
        //Y cursor para acceder a los datos del cursor
//        binding.contactName.text = cursor!!.getString(1)
//        binding.contactEmail.text = cursor.getString(0)
//        binding.contactFav.visibility = if(cursor.getInt(2) == 1) View.VISIBLE else View.GONE
//        if(cursor.getString(3) != null)
//            binding.contactsPhoto.setImageURI(Uri.parse(cursor.getString(3)))
//        else
//            binding.contactsPhoto.visibility = View.GONE
        val contactId = cursor.getString(0)
        //Set the contact name
        binding.contactName.text =
            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
        //Set the contact is favorite
        binding.contactFav.visibility = if(cursor.getInt(2) == 1) View.VISIBLE else View.GONE
        //set the contact photo
        if (cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI)) != null)
            binding.contactsPhoto.setImageURI(
            Uri.parse(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI)))
        ) else binding.contactsPhoto.visibility = View.GONE
        //Get phone number based in contact id
        val phonesQuery = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = $contactId",
            null,
            null
        )
        if (phonesQuery!!.count > 0) {
            phonesQuery.moveToFirst()
            val phoneNumber =
                phonesQuery.getString(phonesQuery.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            binding.buttonPhone.text = phoneNumber
            binding.buttonPhone.setOnClickListener { v ->
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$phoneNumber")
                context.startActivity(intent)
            }
            phonesQuery.close()
        }
        //Get email based in contact id
        val emailsQuery = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
            null,
            "${ContactsContract.CommonDataKinds.Email.CONTACT_ID} = $contactId",
            null,
            null
        )
        if (emailsQuery!!.count > 0) {
            emailsQuery.moveToFirst()
            val email =
                emailsQuery.getString(emailsQuery.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS))
            binding.contactEmail.text = email
            emailsQuery.close()
        }
    }
}
package com.puj.contacts.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.puj.contacts.R

class Alerts {
    private var TAG = Alerts::class.java.name

    fun shortToast(context: Context, message: String?) {
        Log.i(TAG, "shortToast: $message")
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun longToast(context: Context, message: String?) {
        Log.i(TAG, "longToast: $message")
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun shortSimpleSnackbar(parentView: View, message: String) {
        Log.i(TAG, "shortSimpleSnackbar: $message")
        Snackbar.make(parentView, message, Snackbar.LENGTH_SHORT).show()
    }

    fun longSimpleSnackbar(parentView: View, message: String) {
        Log.i(TAG, "longSimpleSnackbar: $message")
        Snackbar.make(parentView, message, Snackbar.LENGTH_LONG).show()
    }

    fun indefiniteSnackbar(parentView: View, message: String) {
        Log.i(TAG, "indefiniteSnackbar: $message")
        val snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(R.string.snackbar_dismiss_label) { snackbar.dismiss() }
        snackbar.show()
    }
}
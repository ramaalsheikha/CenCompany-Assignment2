package com.example.myapplication.constants

import android.content.Context
import android.widget.Toast

object ErrorMessage {
    fun showErrorMessage(context: Context, messageResId: Int) {
        Toast.makeText(context, context.getString(messageResId), Toast.LENGTH_SHORT).show()
    }
}
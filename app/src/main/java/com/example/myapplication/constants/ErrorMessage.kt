package com.example.myapplication.constants

import android.content.Context
import android.util.Log
import android.widget.Toast

object ErrorMessage {
    fun logMessage(key:String,message:String){
        Log.e(key,message)
    }
    fun showErrorMessage(context: Context, messageResId: Int) {
        Toast.makeText(context, context.getString(messageResId), Toast.LENGTH_SHORT).show()
    }
}
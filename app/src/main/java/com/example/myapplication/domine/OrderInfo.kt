package com.example.myapplication.domine

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderInfo(
    var order:MutableList<String>
):Parcelable
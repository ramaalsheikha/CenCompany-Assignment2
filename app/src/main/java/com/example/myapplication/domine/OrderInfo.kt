package com.example.myapplication.domine

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderInfo(
    var  coffeeType: String,
    var coffeeSize: String ,
    var checkBox:MutableList<String>
):Parcelable
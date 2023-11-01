package com.example.myapplication.domine

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PickerTime(
    var hour:Int,
    var minutes:Int,
    var amPm:String
):Parcelable

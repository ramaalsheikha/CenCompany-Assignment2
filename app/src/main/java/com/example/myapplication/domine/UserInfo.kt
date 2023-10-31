package com.example.myapplication.domine

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UserInfo (
    var userName:String,
    var userPhone:String,
):Parcelable

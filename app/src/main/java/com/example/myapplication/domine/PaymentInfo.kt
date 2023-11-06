package com.example.myapplication.domine

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentInfo (
    var paymentLisl:MutableList<String>,

):Parcelable

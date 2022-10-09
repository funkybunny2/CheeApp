package com.example.fightingapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Forecast(
    val temp: String
): Parcelable

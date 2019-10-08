package com.example.kotlinstudentportal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.net.URL

@Parcelize
data class Portal(val name: String, val url: String) : Parcelable
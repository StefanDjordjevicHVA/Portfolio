package com.example.kotlin_gamebacklog

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
public data class Game (

    var title: String,
    var platform: String,
    var releaseDay: Int,
    var releaseMonth: Int,
    var releaseYear: Int,

    @PrimaryKey var id: Long? = null ) : Parcelable
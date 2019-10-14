package com.example.rockpaperscissorskotlin

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "matchTable")
public data class Match(

    @ColumnInfo(name = "match")
    val winner: String,
    val date: String,
    val playerResId: Int,
    val compResId: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
    ) : Parcelable
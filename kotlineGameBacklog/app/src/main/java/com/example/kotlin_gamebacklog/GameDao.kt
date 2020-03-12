package com.example.kotlin_gamebacklog

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameDao
{
    @Insert
    suspend fun insertGame(game: Game)

    @Query("SELECT * FROM Game")
    fun getGameBacklog() : LiveData<Game?>

    @Update
    suspend fun updateGame(note: Game)
}
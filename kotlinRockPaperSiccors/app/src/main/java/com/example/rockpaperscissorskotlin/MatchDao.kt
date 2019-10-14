package com.example.rockpaperscissorskotlin

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MatchDao
{
    @Query("SELECT * FROM matchTable")
    suspend fun getAllMatches() : List<Match>

    @Query("DELETE FROM matchTable")
    suspend fun deleteAllMatches()

    @Insert
    suspend fun insertMatch(match: Match)

    @Delete
    suspend fun deleteMatch(match: Match)
}
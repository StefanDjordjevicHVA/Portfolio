package com.example.rockpaperscissorskotlin

import android.content.Context

public class MatchRepository(context: Context)
{
    private var matchDao: MatchDao

    init
    {
        val matchRoomDatabase = MatchRoomDatabase.getDatabase(context)

        matchDao = matchRoomDatabase!!.matchDao()
    }

    suspend fun getAllMatches() : List<Match>
    {
        return matchDao.getAllMatches()
    }

    suspend fun deleteAllMatches()
    {
        return matchDao.deleteAllMatches()
    }

    suspend fun insertMatch(match: Match)
    {
        return matchDao.insertMatch(match)
    }

    suspend fun deleteMatch(match: Match)
    {
        return matchDao.deleteMatch(match)
    }
}
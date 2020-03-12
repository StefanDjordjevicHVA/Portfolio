package com.example.kotlin_gamebacklog

import android.content.Context
import androidx.lifecycle.LiveData

class GameRepository(context: Context)
{
    private val gameDao: GameDao

    init
    {
        val database = GameBacklogRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getGameBacklog(): LiveData<Game?>
    {
        return gameDao.getGameBacklog()
    }

    suspend fun updateGameBacklog(game: Game)
    {
        gameDao.updateGame(game)
    }
}
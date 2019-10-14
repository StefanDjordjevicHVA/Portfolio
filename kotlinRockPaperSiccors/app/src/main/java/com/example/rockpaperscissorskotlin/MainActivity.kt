package com.example.rockpaperscissorskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var playerResImage: Int = 0
    private var compResImage: Int = 0
    private lateinit var matchRepository: MatchRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setTitle(R.string.app_name)


        btPaper.setOnClickListener { pressPaper() }
        btRock.setOnClickListener { pressRock() }
        btSciccors.setOnClickListener { pressSciccors() }

        matchRepository = MatchRepository(this)


    }

    private fun createMatchHistoryElement(playerResImage: Int, compResImage: Int)
    {
        val sdf = SimpleDateFormat("dd-M-yyyy hh:mm:ss")
        val dateInString = sdf.format(Date())
        val date = sdf.parse(dateInString)

        mainScope.launch {
            val match = Match(
                            winner = tvResult.text.toString(),
                            date = date.toString(),
                            playerResId = playerResImage,
                            compResId = compResImage)
            withContext(Dispatchers.IO)
            {
                matchRepository.insertMatch(match)
            }
        }
    }

    private fun playGame()
    {
        computerMove()
    }

    private fun computerMove()
    {
        val compPlay = (1..3).random()

        when(compPlay)
        {
            1 -> compResImage = R.drawable.rock
            2 -> compResImage = R.drawable.paper
            3 -> compResImage = R.drawable.scissors
        }

        ivComp.setImageResource(compResImage)

        result(playerResImage, compResImage)

        createMatchHistoryElement(playerResImage, compResImage)
    }

    private fun pressRock()
    {
        playerResImage = R.drawable.rock
        ivPlayer.setImageResource(playerResImage)
        playGame()
    }

    private fun pressPaper()
    {
        playerResImage = R.drawable.paper
        ivPlayer.setImageResource(playerResImage)
        playGame()
    }

    private fun pressSciccors()
    {
        playerResImage = R.drawable.scissors
        ivPlayer.setImageResource(playerResImage)
        playGame()
    }

    private fun result(playerMove: Int, compMove: Int)
    {
        if(playerMove == compMove)
        {
            tvResult.setText(R.string.draw_win)
        }
        else if(playerMove == R.drawable.rock && compMove == R.drawable.scissors)
        {
            tvResult.setText(R.string.you_win)
        }
        else if(playerMove == R.drawable.scissors && compMove == R.drawable.paper)
        {
            tvResult.setText(R.string.you_win)
        }
        else if(playerMove == R.drawable.paper && compMove == R.drawable.rock)
        {
            tvResult.setText(R.string.you_win)
        }
        else if(playerMove == R.drawable.scissors && compMove == R.drawable.rock)
        {
            tvResult.setText(R.string.comp_win)
        }
        else if(playerMove == R.drawable.paper && compMove == R.drawable.scissors)
        {
            tvResult.setText(R.string.comp_win)
        }
        else if(playerMove == R.drawable.rock && compMove == R.drawable.paper)
        {
            tvResult.setText(R.string.comp_win)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return when (item.itemId)
        {
            R.id.action_view_history -> {
                startHistoryActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startHistoryActivity()
    {
        val historyActivityIntent = Intent(this, HistoryActivity::class.java)
        startActivity(historyActivityIntent)
    }


}

package com.example.rockpaperscissorskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity()
{
    private var matches = arrayListOf<Match>()
    private var matchAdapter = MatchAdapter(matches)
    private lateinit var matchRepository: MatchRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        matchRepository = MatchRepository(this)
        initViews()
    }

    private fun initViews()
    {
        rvMatches.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvMatches.adapter = matchAdapter
        rvMatches.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        getMatchesListFromDatabase()
    }

    private fun getMatchesListFromDatabase()
    {
        mainScope.launch {
            val matchList = withContext(Dispatchers.IO) {
                matchRepository.getAllMatches()
            }
            this@HistoryActivity.matches.clear()
            this@HistoryActivity.matches.addAll(matchList)
            this@HistoryActivity.matchAdapter.notifyDataSetChanged()

        }
    }
}

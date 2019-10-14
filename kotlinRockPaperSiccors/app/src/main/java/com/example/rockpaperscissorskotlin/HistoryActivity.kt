package com.example.rockpaperscissorskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_history.toolbar
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
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Match History"

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

    private fun deleteAllMatches()
    {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                matchRepository.deleteAllMatches()
            }
            this@HistoryActivity.matches.clear()
            this@HistoryActivity.matchAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.menu_history, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId)
        {
            android.R.id.home ->
            {
                finish()
                true
            }
            R.id.action_delete_all ->
            {
                deleteAllMatches()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}

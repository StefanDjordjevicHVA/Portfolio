package com.example.task5opdracht1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity()
{
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()
    }

    private fun initViews()
    {
        fab.setOnClickListener { view -> Snackbar.make(
            view,
            "Replace with your own action",
            Snackbar.LENGTH_LONG).setAction("Action", null) }
    }

    private fun initViewModel()
    {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.note.observe(this, Observer{note -> if(note != null)
        {
            tvTitle.text = note.title
            tvLastUpdated.text = getString(R.string.last_updated, note.lastUpdated.toString())
            tvNote.text = note.text
          } })
    }
}

package com.example.kotlinstudentportal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.kotlinstudentportal.MainActivity.Companion.PORTAL_EXTRA
import kotlinx.android.synthetic.main.activity_create_portal.*

class CreatePortalActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_portal)
        initView()
    }

    private fun initView()
    {
        // set back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Create your Portal"

        btAddPortal.setOnClickListener { onAddPortalClick() }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        return when (item?.itemId)
        {
            android.R.id.home ->
            {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun onAddPortalClick()
    {
        val portal = Portal(
            etName.text.toString(),
            etUrl.text.toString()
        )

        val portals = intent.getParcelableArrayListExtra<Portal>(PORTALS)

        portals.add(portal)

        val mainActivityIntent = Intent(this, MainActivity::class.java)

        mainActivityIntent.putParcelableArrayListExtra(MainActivity.PORTAL_EXTRA, portals)
        startActivity(mainActivityIntent)
    }

    companion object
    {
        const val PORTALS = "PORTALS"
    }
}

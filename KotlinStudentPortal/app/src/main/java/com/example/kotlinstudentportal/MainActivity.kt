package com.example.kotlinstudentportal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    private val portals = arrayListOf<Portal>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fab.setOnClickListener { onClickNewPortal() }

        initView()


    }



    private fun initView()
    {
        if (intent.getParcelableArrayListExtra<Portal>(PORTAL_EXTRA) != null)
        {
            portals.clear()
            portals.addAll(intent.getParcelableArrayListExtra<Portal>(PORTAL_EXTRA))
            val portalAdapter = PortalAdapter(portals)
            rvPortals.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            rvPortals.adapter = portalAdapter
            portalAdapter.notifyDataSetChanged()
        } else
        {
            val portalAdapter = PortalAdapter(portals)
            rvPortals.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            rvPortals.adapter = portalAdapter
            portalAdapter.notifyDataSetChanged()
        }
    }

    private fun onClickNewPortal()
    {
        val createPortalActivity = Intent(this, CreatePortalActivity::class.java)

        createPortalActivity.putParcelableArrayListExtra(CreatePortalActivity.PORTALS, portals)
        startActivity(createPortalActivity)
    }

    companion object
    {
        const val PORTAL_EXTRA = "PORTAL_EXTRA"
    }
}

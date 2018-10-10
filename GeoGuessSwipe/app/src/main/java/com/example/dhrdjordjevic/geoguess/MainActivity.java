package com.example.dhrdjordjevic.geoguess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    GeoObjectAdapter mAdapter;
    RecyclerView mGeoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<GeoObject> mGeoObjects = new ArrayList<>();

        for(int i = 0; i < GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES.length; i++)
        {
            mGeoObjects.add(new GeoObject(GeoObject.PRE_DEFINED_GEO_OBJECT_NAMES[i],
                    GeoObject.PRE_DEFINED_GEO_OBJECT_IMAGE_IDS[i]));
        }

        mGeoRecyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new
                StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

        mGeoRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new GeoObjectAdapter(this,
                mGeoObjects);
        mGeoRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
                {
                    @Override
                 public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target)
                    {
                        return false;
                    }

                    //Called when a user swipes left or right on a ViewHolder
                    @Override

                 public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir)
                {
                    //Get the index corresponding to the selected position
                    int position = (viewHolder.getAdapterPosition());

                    if(mAdapter.isInEurope(position).equals("yes") && swipeDir == ItemTouchHelper.LEFT)
                    {
                        Toast.makeText(getApplicationContext(),"Good job!", Toast.LENGTH_SHORT).show();
                    } else if(mAdapter.isInEurope(position).equals("no") && swipeDir == ItemTouchHelper.RIGHT)
                    {
                        Toast.makeText(getApplicationContext(),"Good job!", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        Toast.makeText(getApplicationContext(),"You Suck!", Toast.LENGTH_SHORT).show();
                    }

                    mGeoObjects.remove(position);
                    mAdapter.notifyItemRemoved(position);
                }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mGeoRecyclerView);
    }
}

package com.example.dhrdjordjevic.geoguess;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class GeoObjectAdapter extends RecyclerView.Adapter<GeoObjectViewHolder> {

    private Context context;
    public List<GeoObject> listGeoObject;

    public GeoObjectAdapter(Context context, List<GeoObject> listGeoObject)
    {
        this.context = context;
        this.listGeoObject = listGeoObject;
    }

    @Override
    public GeoObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_cell,
                parent, false);
        return new GeoObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GeoObjectViewHolder holder,final int position)
    {
        final GeoObject geoObject = listGeoObject.get(position);

        holder.geoImage.setImageResource(geoObject.getmGeoImageName());
        //holder.geoName.setText(geoObject.getmGeoName());

    }

    @Override
    public int getItemCount() {
        return listGeoObject.size();
    }

    public String isInEurope(int position)
    {
        return listGeoObject.get(position).getmGeoName();
    }
}

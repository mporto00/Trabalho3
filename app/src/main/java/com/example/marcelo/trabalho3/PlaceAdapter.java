package com.example.marcelo.trabalho3;

/**
 * Created by Marcelo on 13/06/2017.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private List<Place> placeList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvPlaceName,tvfavorite;
        public ImageView ivPlaceImage;

        public ViewHolder(View v) {
            super(v);
            tvPlaceName = (TextView) v.findViewById(R.id.place_name);
            tvfavorite = (TextView) v.findViewById(R.id.favorite);
            ivPlaceImage = (ImageView) v.findViewById(R.id.iv_place);

        }
    }

    public PlaceAdapter(List<Place> placeList){
        this.placeList = placeList;
    }

    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item,parent,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PlaceAdapter.ViewHolder holder, int position) {

        Place place = placeList.get(position);
        holder.tvPlaceName.setText(place.getName());
        holder.tvfavorite.setText(place.getfavorite());
        holder.ivPlaceImage.setImageResource(place.getPic());
    }

    public void verMapa(){

    }

    public void updateList(List<Place> list){
        placeList = list;
        notifyDataSetChanged();
    }





    @Override
    public int getItemCount() {
        return placeList.size();
    }
}
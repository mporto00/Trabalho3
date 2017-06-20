package com.example.marcelo.trabalho3;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Marcelo on 16/06/2017.
 */

public class infos extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos);

        Place place = (Place) getIntent().getSerializableExtra("place");

        TextView tvPlaceName = (TextView) findViewById(R.id.place_name);
        ImageView ivPlaceImage = (ImageView) findViewById(R.id.place_image);
        TextView tvPlaceInfo = (TextView) findViewById(R.id.place_info);
        TextView tvPlaceFavorite = (TextView) findViewById(R.id.place_favorite);


        tvPlaceName.setText(place.getName());
        tvPlaceInfo.setText(place.getDescription());
        tvPlaceFavorite.setText(place.getfavorite());
        ivPlaceImage.setImageResource(place.getPic());


    }

    public void openMap (View view){
        TextView tvPlaceName = (TextView) findViewById(R.id.place_name);
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+ tvPlaceName.getText() +", Florianopolis");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void setFavorite (View view){
        TextView tvFavorite = (TextView) findViewById(R.id.place_favorite);
        tvFavorite.setText("Favorito");

    }
}

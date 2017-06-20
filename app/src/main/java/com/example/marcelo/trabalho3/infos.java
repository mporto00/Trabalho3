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
    DBHelper mydb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos);

        Place place = (Place) getIntent().getSerializableExtra("place");

        TextView tvPlaceName = (TextView) findViewById(R.id.place_name);
        ImageView ivPlaceImage = (ImageView) findViewById(R.id.place_image);
        TextView tvPlaceInfo = (TextView) findViewById(R.id.place_info);
        TextView tvPlaceFavorite = (TextView) findViewById(R.id.place_favorite);
        Button btFavorito = (Button) findViewById(R.id.bt_favorite);

        if (tvPlaceFavorite.getText().toString().equals("Favorito")) {
            btFavorito.setText("Remover Favorito");
        } else {
            btFavorito.setText("Marcar Favorito");
        }
        tvPlaceName.setText(place.getName());
        tvPlaceInfo.setText(place.getDescription());
        tvPlaceFavorite.setText(place.getfavorite());
        //ivPlaceImage.setImageResource(place.getPic());


    }

    public void openMap (View view){
        TextView tvPlaceName = (TextView) findViewById(R.id.place_name);
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+ tvPlaceName.getText() +", Florianopolis");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void setFavorite (View view){
        TextView tvPlaceName = (TextView) findViewById(R.id.place_name);
        ImageView ivPlaceImage = (ImageView) findViewById(R.id.place_image);
        TextView tvPlaceInfo = (TextView) findViewById(R.id.place_info);
        TextView tvPlaceFavorite = (TextView) findViewById(R.id.place_favorite);
        Button btFavorito = (Button) findViewById(R.id.bt_favorite);

        String update;
        if (tvPlaceFavorite.getText().toString().equals("Favorito")) {
            update = "";
            btFavorito.setText("Marcar Favorito");
        } else {
            update = "Favorito";
            btFavorito.setText("Remover Favorito");
        }

        mydb = new DBHelper(this);
        mydb.updatePlace(tvPlaceName.getText().toString(),update, "", tvPlaceInfo.getText().toString());

        tvPlaceFavorite.setText(update);

        //get intent where the parameters from previus screen was stored
        Place place = (Place) getIntent().getSerializableExtra("place");
        place.setfavorite(update);

        //create new intent to send as activity result, setting the user's choices
        Intent i = new Intent();
        i.putExtra("place", place);
        i.putExtra("position", getIntent().getIntExtra("position",0));
        setResult(RESULT_OK, i);
    }
}

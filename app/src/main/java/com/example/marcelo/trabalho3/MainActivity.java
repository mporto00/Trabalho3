package com.example.marcelo.trabalho3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Place> placeList;
    private PlaceAdapter placeAdapter;
    private boolean flag_favorites = false;

    String[] places_names = {"Praia dos Ingleses",
            "Museu de Arte de Santa Catarina",
            "Praia da Joaquina",
            "Praia Mole",
            "Praia da Armação do Pântano do Sul",
            "Praia do Santinho",
            "Ilha do Campeche"};

    String[] places_description = {"A praia dos Ingleses está situada no bairro de Ingleses, norte da ilha de Santa Catarina, no municipio de Florianópolis, capital do estado brasileiro de Santa Catarina.",
            "Museu de Arte de Santa Catarina é um museu de arte no estado brasileiro de Santa Catarina. O Museu de Arte de Santa Catarina é uma instituição vinculada à Fundação Catarinense de Cultura",
            "Praia da Joaquina é uma praia oceânica da cidade brasileira de Florianópolis, ao leste da ilha de Santa Catarina, ao sul do Brasil. O ponto procurado por surfistas, já foi sede de alguns campeonatos mundiais de surfe.",
            "Praia Mole é uma praia ao leste na Ilha de Florianópolis em Santa Catarina. Com uma extensão de 960 metros é muito utilizada para a prática de surf e parapente, devido as suas condições geográficas.",
            "A Praia da Armação do Pântano do Sul ou simplesmente Armação é uma praia brasileira situada na cidade de Florianópolis, no estado de Santa Catarina.",
            "A praia do Santinho está situada no distrito de Ingleses do Rio Vermelho, norte da ilha de Santa Catarina, no municipio de Florianópolis, capital do estado brasileiro de Santa Catarina",
            "Ao leste da Ilha de Santa Catarina, da praia do Campeche, pode-se avistar a ilha do Campeche. Situada no litoral do Estado brasileiro de Santa Catarina, no Oceano Atlântico. Faz parte do território do município de Florianópolis."};

    String[] favoritos = {"Favorito", "", "Favorito", "Favorito", "", "", ""};

    int[] pics = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*SQLiteDatabase db = new ;
        StringBuilder query = new StringBuilder();
        query.append("");
        db.execSQL();*/
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        placeList = new ArrayList<>();

        //Adicionando data dos arrays para os cards
        for (int i = 0; i < places_names.length; i++) {
            Place place = new Place(places_names[i], favoritos[i], pics[i], places_description[i]);
            placeList.add(place);
        }

        placeAdapter = new PlaceAdapter(placeList);

        //Definir adapter para o recicler view, alterando os layouts
        mRecyclerView.setAdapter(placeAdapter);
        placeAdapter.notifyDataSetChanged();

        //seta o evento para onclick dos cards criados
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent myIntent = new Intent();
                myIntent.setClass(getApplicationContext(), infos.class);
                myIntent.putExtra("place", placeList.get(position));
                startActivity(myIntent);



            }
        }));

    }
    public void filter(String text){
        List<Place> temp = new ArrayList();
        for(Place p: placeList){
            if(p.getfavorite().equals(text)){
                temp.add(p);
            }
        }
        placeAdapter.updateList(temp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_grid_colunas:
                mLayoutManager = new GridLayoutManager(this, 2);
                mRecyclerView.setLayoutManager(mLayoutManager);
                break;
            case R.id.item_grid_lista:
                mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mLayoutManager);
                break;
            case R.id.item_horizontal:
                mLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
                mRecyclerView.setLayoutManager(mLayoutManager);
                break;
            case R.id.item_favorites:
                if (flag_favorites == false) {
                    filter("Favorito");
                    flag_favorites = true;
                } else {
                    placeAdapter.updateList(placeList);
                    flag_favorites = false;
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
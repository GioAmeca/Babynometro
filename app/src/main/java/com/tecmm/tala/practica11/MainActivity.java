package com.tecmm.tala.practica11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView rv;


    // provisional
    public static final List<Artista> datos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        // Provisional
        Artista ar = new Artista();
        ar.setName("SASHA GREY");
        ar.setPhone("8787837873");
        ar.setRating(5);
        datos.add(ar);
        ar = new Artista();
        ar.setName("MIA KHALIFA");
        ar.setPhone("555555555");
        ar.setRating(4.3);
        datos.add(ar);

        llenarRecycler();

    }

    public void llenarRecycler() {
        if(datos!=null)
        {
            AdaptorRecycler ar = new AdaptorRecycler(this,datos);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            rv.setLayoutManager(llm);
            rv.setAdapter(ar);
        }
    }
}








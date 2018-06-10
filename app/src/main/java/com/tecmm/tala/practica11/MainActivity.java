package com.tecmm.tala.practica11;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView rv;



    // provisional
    public List<Artista> datos ;

    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);



    }

    @Override
    protected void onResume() {
        super.onResume();
        pedirRetrofit();
    }

    void pedirRetrofit() {
        dialog = ProgressDialog.show(this,"", "Buscando datos"
                                     ,true);
        Retrofit retrofit = new Retrofit.Builder().
                             baseUrl(RetrofitInterface.url)
                               .addConverterFactory(GsonConverterFactory.create())
                             .build();
        RetrofitInterface inter = retrofit.create(RetrofitInterface.class);
        Call<List<Artista>> peticion = inter.traerArtistitas();
        peticion.enqueue(new Callback<List<Artista>>() {
            @Override
            public void onResponse(Call<List<Artista>> call, Response<List<Artista>> response) {
                datos = response.body();
                dialog.dismiss();
                llenarRecycler();
            }

            @Override
            public void onFailure(Call<List<Artista>> call, Throwable t) {

                t.printStackTrace();
            }
        });

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








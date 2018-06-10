package com.tecmm.tala.practica11;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.tv.TvContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityComentarios extends AppCompatActivity {

    @BindView(R.id.txtNombreArtista)
    TextView txtNombre;

    @BindView(R.id.ratingBar)
    RatingBar rating;

    @BindView(R.id.txtUsuario)
    TextView txtUsuario;

    @BindView(R.id.edComentario)
    EditText edComentario;

    private Long id;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        ButterKnife.bind(this);

        Intent i =  getIntent();
        txtNombre.setText(i.getStringExtra("name"));
        id=i.getLongExtra("id",-1);
        setTitle("Calificame!!!");

    }

    @OnClick(R.id.btnComentario)
    public void agregarComentario() {
        dialog = ProgressDialog.show(this,"","Grabando datos",true);
        Comentarios comentarios = new Comentarios();
        Artista artista = new Artista();
        artista.setId(id);
        comentarios.setBabies(artista);
        comentarios.setComment(edComentario.getText().toString());
        comentarios.setRating(rating.getRating());
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitInterface.url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitInterface interfaz  = retrofit.create(RetrofitInterface.class);
        Call<Boolean> peticion = interfaz.altaComentario(comentarios);
        peticion.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean boolen = response.body();
                dialog.dismiss();
                if(boolen)
                    mandarmensaje("Se Guardo correctamente");
                else
                    mandarmensaje("Ocurrio un error");
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });

    }

    public void mandarmensaje(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        finish();
    }
}

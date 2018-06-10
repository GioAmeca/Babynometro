package com.tecmm.tala.practica11;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DescripcionArtista extends AppCompatActivity {


    @BindView(R.id.txtTrayectoria)
    TextView txtVida;

    @BindView(R.id.txtTelefono)
    TextView txtTelefono;

    @BindView(R.id.txtLocalizacion)   // Pendiente
    TextView txtLocalizacion;

    @BindView(R.id.txtComentarios)   // Pendiente
    TextView txtComentarios;

    @BindView(R.id.collapser)
    CollapsingToolbarLayout collapser;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.photo)
    ImageView photoVista;

    private Long id;

    ProgressDialog dialog;

    private  static Intent crearIntencion(Activity activity, Artista artista)
    {
        Intent i = new Intent(activity, DescripcionArtista.class);
        i.putExtra("nombre", artista.getName());     /// esto va a cambiar
        i.putExtra("vida",artista.getLife());
        i.putExtra("telefono", artista.getPhone());
        i.putExtra("latitud", artista.getLatitude());
        i.putExtra("altitud", artista.getAltitude());
        String comentarios = "";
        if(artista.getComments() != null ) {
            for (Comentarios comentario : artista.getComments())
                comentarios += comentario.getComment() + "\n";
        }
        i.putExtra("comentarios", comentarios);
        i.putExtra("photo", artista.getPhoto());
        i.putExtra("id",artista.getId());
        return i;
    }

    public static void crearInstancia(Activity activity, Artista artista){
        Intent i = crearIntencion(activity,artista);

        activity.startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_artista);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        collapser.setTitle(i.getStringExtra("nombre"));

        id=i.getLongExtra("id",-1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        dialog = ProgressDialog.show(this,"", "Actualizando datos",true);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitInterface.url).addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitInterface interfaz = retrofit.create(RetrofitInterface.class);
        Call<Artista> peticion = interfaz.traerArtista(id);
        peticion.enqueue(new Callback<Artista>() {
            @Override
            public void onResponse(Call<Artista> call, Response<Artista> response) {
                Artista artista = response.body();
                dialog.dismiss();
                llenarVentana(artista);
            }

            @Override
            public void onFailure(Call<Artista> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void llenarVentana(Artista artista) {   // quitar intencion
        txtVida.setText(artista.getLife());
        txtTelefono.setText(artista.getPhone());
        txtLocalizacion.setText("A: " +
                           artista.getAltitude() +
                                " L: " +
                artista.getLatitude() );
        String comentarios = "";
        if(artista.getComments() != null ) {
            for (Comentarios comentario : artista.getComments())
                comentarios += comentario.getComment() + "\n";
        }
        txtComentarios.setText(comentarios);
        byte[] photo = artista.getPhoto();
        if(photo!=null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(photo, 0, photo.length);
            photoVista.setImageBitmap(bmp);
        }else
        {
            photoVista.setImageResource(R.drawable.img_none);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab_add)
    public void abrirVentana() {
        Intent i = new Intent(this, ActivityComentarios.class);
        Intent ii = getIntent();
        i.putExtra("name", ii.getStringExtra("nombre"));
        i.putExtra("id",id);
        startActivity(i);
    }
}












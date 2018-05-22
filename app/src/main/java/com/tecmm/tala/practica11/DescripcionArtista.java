package com.tecmm.tala.practica11;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private  static Intent crearIntencion(Activity activity, Artista artista)
    {
        Intent i = new Intent(activity, DescripcionArtista.class);
        i.putExtra("nombre", artista.getName());     /// esto va a cambiar
        i.putExtra("vida",artista.getLife());
        i.putExtra("telefono", artista.getPhone());
        i.putExtra("latitud", artista.getLatitude());
        i.putExtra("altitud", artista.getAltitude());
        i.putExtra("comentarios", "asdasdasd");
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

        llenarVentana(i);

    }

    public void llenarVentana(Intent i) {   // quitar intencion
        txtVida.setText(i.getStringExtra("vida"));
        txtTelefono.setText(i.getStringExtra("telefono"));
        txtLocalizacion.setText("A: " +
                           i.getDoubleExtra("altitud", 0 ) +
                                " L: " +
                i.getDoubleExtra("latitud", 0 ) );
        txtComentarios.setText(i.getStringExtra("comentarios"));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }
}












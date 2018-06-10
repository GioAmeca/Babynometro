package com.tecmm.tala.practica11;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.Console;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptorRecycler extends RecyclerView.Adapter
        <AdaptorRecycler.ViewHolderArtista> implements ItemClickListener
{


    private final Context context;
    private List<Artista> datos;


    public AdaptorRecycler(Context context, List<Artista> datos) {
        this.context = context;
        this.datos = datos;
    }

    @NonNull
    @Override
    public ViewHolderArtista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false);
        return new ViewHolderArtista(v, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderArtista holder, int position) {
        Artista artista = datos.get(position);
        holder.txtnombre.setText(artista.getName());
        holder.txtTelefono.setText(artista.getPhone());
        holder.ratingBar.setRating((float)artista.getRating());
        if(artista.checarImagen())
        {
            Bitmap bmp = BitmapFactory.decodeByteArray(artista.getPhoto(),0
            , artista.getPhoto().length);
            holder.cImage.setImageBitmap(bmp);
        } else {
            holder.cImage.setImageResource(R.drawable.img_none);
        }
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    @Override
    public void onItemClick(View view, int position) {

        Log.e("Posición", "" + position);

        DescripcionArtista.crearInstancia((Activity) context,
                datos.get(position));

    }

    public static class ViewHolderArtista extends RecyclerView.ViewHolder
    implements View.OnClickListener
    {

        @BindView(R.id.nombre_artista)
        TextView txtnombre;
        @BindView(R.id.rating)
        RatingBar ratingBar;
        @BindView(R.id.telefono)
        TextView txtTelefono;
        @BindView(R.id.avatar)
        CircleImageView cImage;

        public ItemClickListener listenert;

        public ViewHolderArtista(View itemView, ItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listenert = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listenert.onItemClick(v, getAdapterPosition());
        }
    }
}

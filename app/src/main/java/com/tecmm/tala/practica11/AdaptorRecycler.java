package com.tecmm.tala.practica11;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptorRecycler extends RecyclerView.Adapter
        <AdaptorRecycler.ViewHolderArtista>
{


    @NonNull
    @Override
    public ViewHolderArtista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderArtista holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolderArtista extends RecyclerView.ViewHolder
    {

        @BindView(R.id.nombre_artista)
        TextView txtnombre;
        @BindView(R.id.rating)
        RatingBar ratingBar;
        @BindView(R.id.telefono)
        TextView txtTelefono;
        @BindView(R.id.avatar)
        CircleImageView cImage;

        public ViewHolderArtista(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}

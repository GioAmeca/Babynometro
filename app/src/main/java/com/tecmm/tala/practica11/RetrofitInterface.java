package com.tecmm.tala.practica11;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {

    public static final String url="http://192.168.1.71:4567/";

    @GET("stars-all")
    Call<List<Artista>> traerArtistitas();

    @GET("stars")
    Call<Artista> traerArtista(@Query("id") Long id);

    @POST("addComment")
    Call<Boolean> altaComentario(@Body Comentarios comentarios);


}

package com.tecmm.tala.practica11;

public class Comentarios {
    private Long id;
    private String comment;
    private double rating;
    private Artista babies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Artista getBabies() {
        return babies;
    }

    public void setBabies(Artista babies) {
        this.babies = babies;
    }
}

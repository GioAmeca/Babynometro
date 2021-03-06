package com.tecmm.tala.practica11;


import java.util.Arrays;
import java.util.List;

public class Artista {

    private long id;
    private String name;
    private String phone;
    private byte[] photo;
    private double rating;
    private double altitude;
    private double latitude;
    private String life;

    private List<Comentarios> comments;

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Comentarios> getComments() {
        return comments;
    }

    public void setComments(List<Comentarios> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", rating=" + rating +

                '}';
    }

    public boolean checarImagen() {
        if(photo == null)
            return false;
        else
            return true;
    }
}

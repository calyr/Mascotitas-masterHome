package com.coursera.app.pm.mascotitas;

/**
 * Created by callisaya on 6/27/16.
 */
public class Mascota {

    private String imagen;
    private String nombre;
    private int retuit;
    private int likes;
    private String id;



    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRetuit() {
        return retuit;
    }

    public void setRetuit(int retuit) {
        this.retuit = retuit;
    }

    public Mascota(String id, String imagen, String nombre, int retuit,int likes) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.retuit = retuit;
        this.likes = likes;
    }

    public  Mascota(){

        this.id = "0";
        this.imagen = "0";
        this.nombre = "0";
        this.retuit = 0;
        this.likes = 0;
    }





}

package com.coursera.app.pm.mascotitas;

/**
 * Created by callisaya on 6/27/16.
 */
public class Mascota {

    private int imagen;
    private String nombre;
    private int retuit;

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
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

    public Mascota(int imagen, String nombre, int retuit) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.retuit = retuit;
    }




}

package com.coursera.app.pm.mascotitas.restApi.model;

import com.coursera.app.pm.mascotitas.pojo.Mascota;

import java.util.ArrayList;

/**
 *
 * @author Roberto Carlos Callisaya Mamani
 * @version 1.0
 */
public class MascotaResponse {

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    ArrayList<Mascota> mascotas;


}

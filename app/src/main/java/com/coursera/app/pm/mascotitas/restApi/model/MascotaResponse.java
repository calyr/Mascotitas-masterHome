package com.coursera.app.pm.mascotitas.restApi.model;

import com.coursera.app.pm.mascotitas.pojo.Mascota;

import java.util.ArrayList;

/**
 * <p> The MascotaResponse class</p>
 * <p> Nuevatel PCS de Bolivia S.A. (c) 2016.</p>
 * <p>
 * <p>El contenido de este archivo esta clasificado como: </p>
 * <p> INFORMACION DE CONFIDENCIALIDAD ALTA </p>
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

package com.coursera.app.pm.mascotitas.restApi.model;

import com.coursera.app.pm.mascotitas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by calyr on 1/2/17.
 */
public class FollowResponse {
    public ArrayList<UsuarioResponse> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(ArrayList<UsuarioResponse> seguidores) {
        this.seguidores = seguidores;
    }

    ArrayList<UsuarioResponse> seguidores;
}

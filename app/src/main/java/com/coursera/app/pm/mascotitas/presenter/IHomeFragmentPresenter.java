package com.coursera.app.pm.mascotitas.presenter;

import com.coursera.app.pm.mascotitas.Mascota;

import java.util.ArrayList;

/**
 * Created by calyr on 7/10/16.
 */
public interface IHomeFragmentPresenter {

    public void obtenerMascotasBaseDatos();

    public void mostrarMascotasRV();

    public void obtenerMediosRecientes();
}

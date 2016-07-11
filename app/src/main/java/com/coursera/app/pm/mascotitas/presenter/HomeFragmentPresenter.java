package com.coursera.app.pm.mascotitas.presenter;

import android.content.Context;

import com.coursera.app.pm.mascotitas.Mascota;
import com.coursera.app.pm.mascotitas.db.ManagerMascotas;
import com.coursera.app.pm.mascotitas.view.IHomeFragmentView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by calyr on 7/10/16.
 */
public class HomeFragmentPresenter implements IHomeFragmentPresenter {

    private IHomeFragmentView iHomeFragmentView;
    private Context contexto;
    private ManagerMascotas manager;
    private ArrayList<Mascota> mascotas;
    public HomeFragmentPresenter(IHomeFragmentView iHomeFragmentView, Context contexto) {
        this.iHomeFragmentView = iHomeFragmentView;
        this.contexto = contexto;
        this.obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        manager = new ManagerMascotas(contexto);
        mascotas = manager.allMascotas();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iHomeFragmentView.initAdapterRvHome(iHomeFragmentView.createAdapter(mascotas));

    }
}

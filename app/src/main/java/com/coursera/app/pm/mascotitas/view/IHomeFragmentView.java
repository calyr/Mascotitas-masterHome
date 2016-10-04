package com.coursera.app.pm.mascotitas.view;

import com.coursera.app.pm.mascotitas.pojo.Mascota;
import com.coursera.app.pm.mascotitas.MascotaAdapter;

import java.util.ArrayList;

/**
 * Created by calyr on 7/10/16.
 */
public interface IHomeFragmentView {

    public void initAdapterRvHome(MascotaAdapter adaptador);

    public void generarLinearLayoutVertical();

    public MascotaAdapter createAdapter(ArrayList<Mascota> mascotas);
}

package com.coursera.app.pm.mascotitas.view;

import com.coursera.app.pm.mascotitas.MascotaAdapter;
import com.coursera.app.pm.mascotitas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by calyr on 1/2/17.
 */
public interface IPerfilFragmentView {
    public void initAdapterRvHome(MascotaAdapter adaptador);

    public void generarLinearLayoutVertical();

    public MascotaAdapter createAdapter(ArrayList<Mascota> mascotas);
}

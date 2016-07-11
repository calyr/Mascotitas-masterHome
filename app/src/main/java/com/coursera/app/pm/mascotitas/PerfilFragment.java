package com.coursera.app.pm.mascotitas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rv;

    public PerfilFragment() {
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(1,R.drawable.primero, "Rambo", 0,0));
        mascotas.add(new Mascota(2,R.drawable.segundo, "Dina", 1,0));
        mascotas.add(new Mascota(3,R.drawable.tercero, "Perla", 2,0));
        mascotas.add(new Mascota(4,R.drawable.cuarto, "Steben", 3,0));
        mascotas.add(new Mascota(5,R.drawable.quinto, "Choco", 3,0));
        mascotas.add(new Mascota(6,R.drawable.sexto, "Filulay", 3,0));
        mascotas.add(new Mascota(7,R.drawable.septimo, "Betoben", 3,0));
        mascotas.add(new Mascota(8,R.drawable.octavo, "Mimo", 3,0));


    }

    private void inicializarListaMascotas() {
        MascotaAdapter ada = new MascotaAdapter(mascotas, 1, this.getActivity());
        rv.setAdapter(ada);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        rv = (RecyclerView) v.findViewById(R.id.rvMascotasPerfil);

        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);

        //LinearLayoutManager llm = new LinearLayoutManager( getActivity() );
        //llm.setOrientation( LinearLayoutManager.VERTICAL );

        rv.setLayoutManager(glm);
        //rv.setLayoutManager(llm);
        inicializarListaMascotas();
        return v;
    }


}

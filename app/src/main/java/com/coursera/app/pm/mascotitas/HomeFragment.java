package com.coursera.app.pm.mascotitas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rv;

    public HomeFragment() {
        // Required empty public constructor

        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.primero, "Rambo", 0));
        mascotas.add(new Mascota(R.drawable.segundo, "Dina", 1));
        mascotas.add(new Mascota(R.drawable.tercero, "Perla", 2));
        mascotas.add(new Mascota(R.drawable.cuarto, "Steben", 3));
        mascotas.add(new Mascota(R.drawable.quinto, "Choco", 3));
        mascotas.add(new Mascota(R.drawable.sexto, "Filulay", 3));
        mascotas.add(new Mascota(R.drawable.septimo, "Betoben", 3));
        mascotas.add(new Mascota(R.drawable.octavo, "Mimo", 3));


    }

    private void inicializarListaMascotas() {
        MascotaAdapter ada = new MascotaAdapter(mascotas, 0);
        rv.setAdapter(ada);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        rv = (RecyclerView) v.findViewById(R.id.rvMascotas);

        //GridLayoutManager glm = new GridLayoutManager(this,2);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //rv.setLayoutManager(glm);
        rv.setLayoutManager(llm);
        inicializarListaMascotas();

        return v;
        //
    }


}

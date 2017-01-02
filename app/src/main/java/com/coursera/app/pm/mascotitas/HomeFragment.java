package com.coursera.app.pm.mascotitas;


import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coursera.app.pm.mascotitas.presenter.HomeFragmentPresenter;
import com.coursera.app.pm.mascotitas.presenter.IHomeFragmentPresenter;
import com.coursera.app.pm.mascotitas.view.IHomeFragmentView;
import com.coursera.app.pm.mascotitas.pojo.Mascota;


import java.sql.SQLOutput;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements IHomeFragmentView {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rv;
    private IHomeFragmentPresenter presenter;

    public HomeFragment() {

        System.out.println("Ingrese al home fragment");


        // Required empty public constructor

        /*mascotas = new ArrayList<Mascota>();
*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        rv = (RecyclerView) v.findViewById(R.id.rvMascotas);
        //GridLayoutManager glm = new GridLayoutManager(this,2);
        this.generarLinearLayoutVertical();


        //initAdapterRvHome(createAdapter(mascotas));
        presenter = new HomeFragmentPresenter(this,getContext());
        return v;
    }

    @Override
    public void initAdapterRvHome(MascotaAdapter adapter) {

        rv.setAdapter(adapter);
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //rv.setLayoutManager(glm);
        rv.setLayoutManager(llm);
    }

    @Override
    public MascotaAdapter createAdapter(ArrayList<Mascota> mascotas) {
        MascotaAdapter ada = new MascotaAdapter(mascotas, 0,this.getActivity());
        return ada;
    }
}
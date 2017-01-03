package com.coursera.app.pm.mascotitas;

import com.coursera.app.pm.mascotitas.MascotaAdapter;
import com.coursera.app.pm.mascotitas.pojo.Mascota;
import com.coursera.app.pm.mascotitas.presenter.HomeFragmentPresenter;
import com.coursera.app.pm.mascotitas.presenter.IPerfilFragmentPresenter;
import com.coursera.app.pm.mascotitas.presenter.PerfilFragmentPresenter;
import com.coursera.app.pm.mascotitas.session.SessionManager;
import com.coursera.app.pm.mascotitas.view.IPerfilFragmentView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

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
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements IPerfilFragmentView {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rv;
    private IPerfilFragmentPresenter presenter;
    private TextView perfilName;
    SessionManager session;
    private CircularImageView circularImageView;


    public PerfilFragment() {
        mascotas = new ArrayList<Mascota>();
        System.out.println("Ingreso al PerfilFragment New");
        //mascotas.add(new Mascota(1,R.drawable.primero, "Rambo", 0,0));
        //mascotas.add(new Mascota(2,R.drawable.segundo, "Dina", 1,0));
        //mascotas.add(new Mascota(3,R.drawable.tercero, "Perla", 2,0));
        //mascotas.add(new Mascota(4,R.drawable.cuarto, "Steben", 3,0));
        //mascotas.add(new Mascota(5,R.drawable.quinto, "Choco", 3,0));
        //mascotas.add(new Mascota(6,R.drawable.sexto, "Filulay", 3,0));
        //mascotas.add(new Mascota(7,R.drawable.septimo, "Betoben", 3,0));
        //mascotas.add(new Mascota(8,R.drawable.octavo, "Mimo", 3,0));


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
        perfilName = (TextView) v.findViewById(R.id.perfilNombre);
        session =  new SessionManager(v.getContext());
        circularImageView = (CircularImageView)v.findViewById(R.id.perfilImgFoto);

        if(session.isLoggedIn() == true){
            System.out.println("EL usuario es  real" + session.getUser().toString());
            perfilName.setText(session.getUser().get("USERNAME"));
            Picasso.with(getActivity())
                    .load(session.getUser().get("USERNAMEPHOTO"))
                    .placeholder(R.drawable.bone)
                    .into(circularImageView);
        }else{
            perfilName.setText("Perfil no configurado. Puedes probar con (duquedux  puppies.januan petagmem)");

        }


       // GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        this.generarLinearLayoutVertical();

        //LinearLayoutManager llm = new LinearLayoutManager( getActivity() );
        //llm.setOrientation( LinearLayoutManager.VERTICAL );

     //   rv.setLayoutManager(glm);
        //rv.setLayoutManager(llm);
 //       inicializarListaMascotas();
        System.out.println("INGRESANDO PERFILFRAGMENT");
        presenter = new PerfilFragmentPresenter(this,getContext());
        System.out.println("INGRESANDO PERFILFRAMGNET CREATE PRESENTER");
        return v;
    }


    @Override
    public void initAdapterRvHome(MascotaAdapter adaptador) {
        rv.setAdapter(adaptador);

    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);

        rv.setLayoutManager(glm);
        //rv.setLayoutManager(llm);
    }

    @Override
    public MascotaAdapter createAdapter(ArrayList<Mascota> mascotas) {
        MascotaAdapter ada = new MascotaAdapter(mascotas, 0,this.getActivity());
        return ada;
    }
}
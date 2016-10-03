package com.coursera.app.pm.mascotitas.presenter;

import android.content.Context;

import com.coursera.app.pm.mascotitas.Mascota;
import com.coursera.app.pm.mascotitas.db.ManagerMascotas;
import com.coursera.app.pm.mascotitas.restApi.EndpointsApi;
import com.coursera.app.pm.mascotitas.restApi.adapter.RestApiAdapter;
import com.coursera.app.pm.mascotitas.restApi.model.MascotaResponse;
import com.coursera.app.pm.mascotitas.view.IHomeFragmentView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by calyr on 7/10/16.
 */
public class HomeFragmentPresenter implements IHomeFragmentPresenter {

    private IHomeFragmentView iHomeFragmentView;
    private Context contexto;
    private ManagerMascotas manager;
    public ArrayList<Mascota> mascotas;
    public HomeFragmentPresenter(IHomeFragmentView iHomeFragmentView, Context contexto) {
        this.iHomeFragmentView = iHomeFragmentView;
        this.contexto = contexto;
        //this.obtenerMascotasBaseDatos();
        this.obtenerMediosRecientes();
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

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagran();
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia();

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {


            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
               MascotaResponse lista = response.body();

               //this.mascotas = lista.getMascotas();
               mostrarMascotasRV();

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {

            }
        });
    }


}

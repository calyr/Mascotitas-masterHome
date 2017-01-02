package com.coursera.app.pm.mascotitas;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.coursera.app.pm.mascotitas.restApi.ConstantesRestApi;
import com.coursera.app.pm.mascotitas.restApi.EndpointsApi;
import com.coursera.app.pm.mascotitas.restApi.adapter.RestApiAdapter;
import com.coursera.app.pm.mascotitas.restApi.model.FollowResponse;
import com.coursera.app.pm.mascotitas.restApi.model.MascotaResponse;
import com.coursera.app.pm.mascotitas.restApi.model.UsuarioResponse;
import com.coursera.app.pm.mascotitas.session.SessionManager;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfigurarCuentaActivity extends AppCompatActivity {

    private static final String TAG = ConfigurarCuentaActivity.class.getSimpleName();
    private Button configurar;
    private Toolbar toolbar;
    SessionManager session;
    TextInputEditText accountUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);
        session =  new SessionManager(getApplicationContext());

        accountUser = (TextInputEditText) findViewById(R.id.config_account_form_name);

        if(session.isLoggedIn() == true){
            accountUser.setText(session.getUser().get("name"));
        }else{
            //accountUser.( R.string.hintpetagram);
        }

        toolbar = (Toolbar) findViewById(R.id.toolbarConfig);

        if ( toolbar != null){
            setSupportActionBar(toolbar);
        }
        //Agregamos el Icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.bone);

        //Cambiamos el título de la toolbar
        getSupportActionBar().setTitle("    Configurar Cuenta");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void guardarCuenta(View view) {

        boolean cancel = false;
        String userInstagram = accountUser.getEditableText().toString();
        View focusView = null;
        if(TextUtils.isEmpty(userInstagram)){
            accountUser.setError("El campo no puede ser vacio");
            cancel = true;
            focusView = accountUser;
        }

        if(cancel){
            focusView.requestFocus();
        }else{
            //session.createSession(userInstagram);
            configurarAccount(userInstagram);
            Toast.makeText(this, "Guardando cuenta..... Espere porfavor " +userInstagram, Toast.LENGTH_LONG).show();

        }

    }
    
    public void configurarAccount(final String userInstagram){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.contruyeGsonDeserializadorFollow();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagran(gsonMediaRecent);
        Call<FollowResponse> mascotaResponseCall = endpointsApi.getRecentMediaFollow();
        Log.d(TAG, "mEtodo configurarAccount" );
        mascotaResponseCall.enqueue(new Callback<FollowResponse>(){
            @Override
            public void onResponse(Call<FollowResponse> call, Response<FollowResponse> response) {
                FollowResponse lista = response.body();
                Log.d(TAG, "INGRESO AL WEB SERVICE FOLLOW");
                Log.d(TAG, lista.getSeguidores().size() + " === ");
                int contador = 0;
                for(int i=0;i< lista.getSeguidores().size(); i++){
                    if(lista.getSeguidores().get(i).getUserInstagram().equals(userInstagram)){
                        contador = contador + 1;

                        Log.d(TAG, "onResponse: " + " este usuario si existe ");
                        Toast.makeText(getApplicationContext(), R.string.follow_correct, Toast.LENGTH_LONG).show();
                        session.createSession(userInstagram,
                                lista.getSeguidores().get(i).getFullName(),
                                lista.getSeguidores().get(i).getId(),
                                lista.getSeguidores().get(i).getUrlImage());
                         Intent intent = new Intent(ConfigurarCuentaActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }

                if(contador == 0){
                    Toast.makeText(getApplicationContext(), R.string.follow_incorrect, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onResponse: " + userInstagram + " = " );
                }
                //Log.d(TAG, lista.getMascotas().toString());
                Log.d(TAG, ConstantesRestApi.URL_BASE);
                Log.d(TAG, ConstantesRestApi.URL_GET_RECENT_MEDIA_USER);
                //mascotas = lista.getMascotas();
                //mostrarMascotasRV();
            }
            @Override
            public void onFailure(Call<FollowResponse> call, Throwable t) {
                Log.d(TAG, "Ocurrio un error");
                Log.e(TAG, t.toString());

            }
        });


    }
}

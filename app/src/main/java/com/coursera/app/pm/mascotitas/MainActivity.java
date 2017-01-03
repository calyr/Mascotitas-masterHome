package com.coursera.app.pm.mascotitas;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.coursera.app.pm.mascotitas.restApi.EndpointsApi;
import com.coursera.app.pm.mascotitas.restApi.adapter.RestApiAdapter;
import com.coursera.app.pm.mascotitas.restApi.model.UsuarioResponse;
import com.google.firebase.iid.FirebaseInstanceId;
import com.coursera.app.pm.mascotitas.adapter.PageAdapter;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Toolbar mToolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();

        Intent intent = this.getIntent();
        Bundle parametros = intent.getExtras();


        if(parametros !=  null){
            String flagtab = parametros.get("flagtab").toString();

            if (  flagtab.equals("ONE") ){
                viewPager.setCurrentItem(0);
            }else{
                viewPager.setCurrentItem(1);

            }


        }





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mAbout) {
            Toast.makeText(this, "Acerca de", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        } else if (id == R.id.mContact) {
            Toast.makeText(this, "Contacto", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ContactoActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_star) {
            //Toast.makeText(this, "detalle", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, DetalleActivity.class);
            startActivity(intent);
        } else if ( id == R.id.mConfigAccount){

            Intent intent = new Intent(this, ConfigurarCuentaActivity.class);
            startActivity(intent);

        } else if ( id == R.id.mReceiveNotif){
            String token = FirebaseInstanceId.getInstance().getToken();
            Log.d(TAG, "onMessageReceived:  "+ token);

            String userInstagram =  "calyr123";

            RestApiAdapter restApiAdapter = new RestApiAdapter();
            EndpointsApi endpoints = restApiAdapter.establecerConexionRestAPI();
            Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarTokenId(token, userInstagram);

            usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                @Override
                public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                    UsuarioResponse usuarioResponse = response.body();
                    Log.d("ID FIREBASE", usuarioResponse.getId());
                    Log.d("TOKEN FIREBASE", usuarioResponse.getToken());
                }

                @Override
                public void onFailure(Call<UsuarioResponse> call, Throwable t) {

                }
            });
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new HomeFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_action_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_perfil);


    }
}

package com.coursera.app.pm.mascotitas;

import com.coursera.app.pm.mascotitas.pojo.Mascota;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


import com.coursera.app.pm.mascotitas.db.ManagerMascotas;

import java.util.ArrayList;

public class DetalleActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<Mascota> mascotas;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        if( mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        ManagerMascotas manager = new ManagerMascotas(this);


        mascotas = manager.fiveRetuitMascotas();

        if(mascotas.size()==0){
            Toast.makeText(this,"No hay ninguna mascota retuiteada", Toast.LENGTH_LONG).show();
            Toast.makeText(this,"Haz click en el Nombre o en la Imagen.", Toast.LENGTH_LONG).show();

        }
        rv = (RecyclerView) findViewById(R.id.rvMascotasDetalle);

        //GridLayoutManager glm = new GridLayoutManager(this,2);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation( LinearLayoutManager.VERTICAL );

        //rv.setLayoutManager(glm);
        rv.setLayoutManager(llm);
        inicializarListaMascotas();

    }

    private void inicializarListaMascotas() {
        MascotaAdapter ada = new MascotaAdapter(mascotas, 0, this);
        rv.setAdapter( ada );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.action_star) {
            Intent intent = new Intent(this, DetalleActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}

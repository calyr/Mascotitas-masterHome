package com.coursera.app.pm.mascotitas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

public class ConfigurarCuentaActivity extends AppCompatActivity {

    private Button configurar;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        toolbar = (Toolbar) findViewById(R.id.toolbarConfig);

        if ( toolbar != null){
            setSupportActionBar(toolbar);
        }
        //Agregamos el Icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.bone);

        //Cambiamos el título de la toolbar
        getSupportActionBar().setTitle("    Configurar Cuenta");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

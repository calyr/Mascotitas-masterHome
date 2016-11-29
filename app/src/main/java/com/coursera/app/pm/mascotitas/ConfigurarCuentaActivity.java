package com.coursera.app.pm.mascotitas;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.coursera.app.pm.mascotitas.session.SessionManager;

public class ConfigurarCuentaActivity extends AppCompatActivity {

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
            session.createSession(userInstagram);
            Toast.makeText(this, "Se guardo la cuenta " +userInstagram, Toast.LENGTH_LONG).show();

        }

    }
}

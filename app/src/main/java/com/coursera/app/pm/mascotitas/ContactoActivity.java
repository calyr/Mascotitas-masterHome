package com.coursera.app.pm.mascotitas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ContactoActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private EditText nombre;
    private EditText correo;
    private EditText mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nombre = (EditText) findViewById(R.id.form_name);
        correo = (EditText) findViewById(R.id.form_email);
        mensaje = (EditText) findViewById(R.id.form_description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacto, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    public void confirmarDatos(View view) {
        Toast.makeText(this, "Se esta enviando el correo.", Toast.LENGTH_LONG).show();
            //Getting content for email
            String email = correo.getText().toString().trim();
            String subject = "Mensaje de applicacion";//nombre.getText().toString().trim();
            String message = mensaje.getText().toString().trim();

            //Creating SendMail object
            SendMail sm = new SendMail(this, email, subject, message);

            //Executing sendmail to send email
            sm.execute();

    }
}

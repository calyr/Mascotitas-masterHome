package com.coursera.app.pm.mascotitas;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.coursera.app.pm.mascotitas.restApi.EndpointsApi;
import com.coursera.app.pm.mascotitas.restApi.adapter.RestApiAdapter;
import com.coursera.app.pm.mascotitas.restApi.model.UsuarioResponse;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactoActivity extends AppCompatActivity {
    private static final String TAG = "ContactoActivity" ;
    private Toolbar toolbar;
    private EditText nombre;
    private EditText correo;
    private EditText mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        toolbar = (Toolbar) findViewById(R.id.toolbarContacto);

        if ( toolbar != null){
            setSupportActionBar(toolbar);
        }
        //Agregamos el Icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.bone);

        //Cambiamos el título de la toolbar
        getSupportActionBar().setTitle("    Contacto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nombre = (EditText) findViewById(R.id.form_name);
        correo = (EditText) findViewById(R.id.form_email);
        mensaje = (EditText) findViewById(R.id.form_description);


        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        //NOFICACION FOLLOW
        Intent iFollow = new Intent();
        iFollow.setAction("FOLLOW");
        PendingIntent pendingIntentFollow = PendingIntent.getBroadcast(this, 0, iFollow, PendingIntent.FLAG_UPDATE_CURRENT);

        android.support.v7.app.NotificationCompat.Action actionFollow =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_dog,getString(R.string.texto_accion_toque_follow),pendingIntentFollow)
                .build();

        //NOTIFICACION PERFIL

        Intent iPerfil = new Intent(this, MainActivity.class);
        iPerfil.putExtra("flagtab","TWO");
        iPerfil.setAction("PERFIL");
        PendingIntent pendingIntentPerfil = PendingIntent.getBroadcast(this, 0, iPerfil, PendingIntent.FLAG_UPDATE_CURRENT);

        android.support.v7.app.NotificationCompat.Action actionPerfil =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_dog,getString(R.string.texto_accion_toque_perfil),pendingIntentPerfil)
                        .build();

        //NOTIFICACION HOME

        Intent iHome = new Intent();
        iHome.setAction("HOME");

        PendingIntent pendingIntentHome = PendingIntent.getBroadcast(this, 0, iHome, PendingIntent.FLAG_UPDATE_CURRENT);
        android.support.v7.app.NotificationCompat.Action actionHome =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_dog,getString(R.string.texto_accion_toque_home),pendingIntentHome)
                        .build();


        android.support.v7.app.NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.backgroundwear))
                .setGravity(Gravity.CENTER_VERTICAL);




        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bone)
                .setContentTitle("Notificación")
                .setContentText("Hola Mundo")
                .setSound(sonido)
                .setAutoCancel(true)
                .extend(wearableExtender.addAction(actionFollow).addAction(actionHome).addAction(actionPerfil))


                ;

        NotificationManagerCompat notificationManager = (NotificationManagerCompat.from(this)) ;
        notificationManager.notify(0, notification.build());

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

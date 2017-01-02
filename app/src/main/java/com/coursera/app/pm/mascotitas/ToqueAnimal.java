package com.coursera.app.pm.mascotitas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by calyr on 1/1/17.
 */
public class ToqueAnimal extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String ACCION_KEY = "TOQUE_ANIMAL";
        String accion = intent.getAction();
        if  ( ACCION_KEY.equals(accion)){
            toqueAnimal();
            Toast.makeText(context, "Diste un toque a un perro", Toast.LENGTH_SHORT ).show();
        }
        toqueAnimal();
    }

    public void toqueAnimal(){
        System.out.println("Aqui es donde se coloca la llamada RESTFUL");
    }
}

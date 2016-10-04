package com.coursera.app.pm.mascotitas.db;

import android.content.ContentValues;
import android.content.Context;

import com.coursera.app.pm.mascotitas.pojo.Mascota;
import com.coursera.app.pm.mascotitas.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by calyr on 7/10/16.
 */
public class ManagerMascotas {
    private static final int RETUIT = 1;
    private Context contexto;

    public ManagerMascotas(Context contexto) {
        this.contexto = contexto;
    }

    public ArrayList<Mascota> allMascotas(){

        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        /*mascotas.add(new Mascota(R.drawable.primero, "Rambito", 0,0));
        mascotas.add(new Mascota(R.drawable.segundo, "Dina", 1,0));
        mascotas.add(new Mascota(R.drawable.tercero, "Perla", 2,0));
        mascotas.add(new Mascota(R.drawable.cuarto, "Steben", 3,0));
        mascotas.add(new Mascota(R.drawable.quinto, "Choco", 3,0));
        mascotas.add(new Mascota(R.drawable.sexto, "Filulay", 3,0));
        mascotas.add(new Mascota(R.drawable.septimo, "Betoben", 3,0));
        mascotas.add(new Mascota(R.drawable.octavo, "Mimo", 3,0));
        */
        BaseDatos db = new BaseDatos(contexto);
        //db.onUpgrade(db,1,1);
        insertar10Mascotas(db);
        return db.getAllMascotas();


    }

    public void insertar10Mascotas(BaseDatos db){

        /*
        mascotas.add(new Mascota(R.drawable.quinto, "", 3,0));
        mascotas.add(new Mascota(R.drawable.sexto, "", 3,0));
        mascotas.add(new Mascota(R.drawable.septimo, "", 3,0));
        mascotas.add(new Mascota(R.drawable.octavo, "", 3, 0));
        */

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Rambo");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN,R.drawable.primero);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RETUIT,0);
        db.loadDummy(contentValues);

         contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Dina");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN,R.drawable.segundo);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RETUIT,0);
        db.loadDummy(contentValues);

         contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Perla");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN,R.drawable.tercero);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RETUIT,0);
        db.loadDummy(contentValues);

         contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Steben");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN,R.drawable.cuarto);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RETUIT,0);
        db.loadDummy(contentValues);

         contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Choco");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN,R.drawable.quinto);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RETUIT,0);
        db.loadDummy(contentValues);

         contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Filulay");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN,R.drawable.sexto);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RETUIT,0);
        db.loadDummy(contentValues);

         contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Betoben");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN,R.drawable.septimo);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RETUIT,0);
        db.loadDummy(contentValues);

         contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE,"Mimo");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN,R.drawable.octavo);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RETUIT,0);
        db.loadDummy(contentValues);
    }

    public void retuitMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(contexto);

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTARETUIT_MASCOTA_ID,mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTARETUIT_NUMERO_RETUIT,RETUIT);
        db.insertarRetuit(contentValues);

    }

    public int getRetuitMascota(Mascota mascota){

        BaseDatos db = new BaseDatos(contexto);

       return db.obtenerRetuitMascota(mascota);
    }

    public ArrayList<Mascota> fiveRetuitMascotas(){
        BaseDatos db = new BaseDatos(contexto);
        return db.getFiveRetuitMascotas();
    }



}

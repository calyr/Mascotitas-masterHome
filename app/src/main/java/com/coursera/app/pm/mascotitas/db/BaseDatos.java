package com.coursera.app.pm.mascotitas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.coursera.app.pm.mascotitas.pojo.Mascota;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by calyr on 7/10/16.
 */
public class BaseDatos extends SQLiteOpenHelper {

    private Context contexto;
    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE "+ ConstantesBaseDatos.TABLE_MASCOTA + "("+
                ConstantesBaseDatos.TABLE_MASCOTA_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE+ " TEXT,"+
                ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN+ " INTEGER,"+
                ConstantesBaseDatos.TABLE_MASCOTA_RETUIT+ " INTEGER"+
                ")";

        String queryCrearTablaMascotaRetuit = "CREATE TABLE "+ ConstantesBaseDatos.TABLE_MASCOTARETUIT + "("+
                ConstantesBaseDatos.TABLE_MASCOTARETUIT_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ConstantesBaseDatos.TABLE_MASCOTARETUIT_MASCOTA_ID+ " INTEGER,"+
                ConstantesBaseDatos.TABLE_MASCOTARETUIT_NUMERO_RETUIT+ " INTEGER,"+
                " FOREIGN KEY(" +ConstantesBaseDatos.TABLE_MASCOTARETUIT_MASCOTA_ID +") "+
                " REFERENCES "+ ConstantesBaseDatos.TABLE_MASCOTA + "("+ ConstantesBaseDatos.TABLE_MASCOTA_ID +")"+
                ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaMascotaRetuit);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+ConstantesBaseDatos.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXIST "+ConstantesBaseDatos.TABLE_MASCOTARETUIT);
        onCreate(db);
    }

    public ArrayList<Mascota> getAllMascotas(){

        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM "+ ConstantesBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rows = db.rawQuery(query, null);
//
//        while(rows.moveToNext()){
//
//           // public Mascota(int id, int imagen, String nombre, int retuit,int likes) {
//
//                Mascota mascota = new Mascota( rows.getInt(0), rows.getInt(2), rows.getString(1), rows.getInt(3), 0);
//                mascota.setRetuit( this.obtenerRetuitMascota(mascota));
//                mascotas.add(mascota);
//        }
//        db.close();
        return mascotas;
    }


    public ArrayList<Mascota> getFiveRetuitMascotas(){

        ArrayList<Mascota> mascotas = new ArrayList<>();

        //String query = "SELECT * FROM "+ ConstantesBaseDatos.TABLE_MASCOTA+ " limit 3";
//      String query = "select * from (select mascota_id,count(*) cantidad from
//                                                                                 mascota_retuit group by mascota_id order by cantidad desc limit 5) retuit, mascota m where retuit.mascota_id = m.id";
        String query = "select m.id, m.imagen, m.nombre, cantidad from (select "+ConstantesBaseDatos.TABLE_MASCOTARETUIT_MASCOTA_ID+",count(*) cantidad from "+
                ConstantesBaseDatos.TABLE_MASCOTARETUIT+" group by "+ConstantesBaseDatos.TABLE_MASCOTARETUIT_MASCOTA_ID+" order by cantidad desc limit 5) retuit, "+ConstantesBaseDatos.TABLE_MASCOTA+" m where retuit.mascota_id = m.id";

        Log.d("MYQUERY", query);
        System.out.print(query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rows = db.rawQuery(query, null);

       //while(rows.moveToNext()){

       //    // public Mascota(int id, int imagen, String nombre, int retuit,int likes) {

       //    Mascota mascota = new Mascota( rows.getInt(0), rows.getInt(1), rows.getString(2), rows.getInt(3), 0);
       //    //mascota.setRetuit( this.obtenerRetuitMascota(mascota));
       //    mascotas.add(mascota);
       //}
        db.close();
        return mascotas;
    }

    public void loadDummy(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert( ConstantesBaseDatos.TABLE_MASCOTA, null, contentValues);
        db.close();

    }

    public void insertarRetuit(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert( ConstantesBaseDatos.TABLE_MASCOTARETUIT, null, contentValues);
        db.close();

    }

    public int obtenerRetuitMascota(Mascota mascota){
        int retuit = 0;
        String query = "SELECT count(*) FROM "+ConstantesBaseDatos.TABLE_MASCOTARETUIT +
                " WHERE "+ConstantesBaseDatos.TABLE_MASCOTARETUIT_MASCOTA_ID + "="+ mascota.getId();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor rows = db.rawQuery(query, null);

        if( rows.moveToNext()){
            retuit = rows.getInt(0);
        }
        db.close();
        return retuit;
    }
}

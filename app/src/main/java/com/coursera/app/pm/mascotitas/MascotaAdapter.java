package com.coursera.app.pm.mascotitas;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by callisaya on 6/27/16.
 */
public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {


    ArrayList<Mascota> mascotas;
    private int tipo = 0;

    public MascotaAdapter(ArrayList<Mascota> mascotas, int tipo) {
        this.mascotas = mascotas;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;
        if (tipo == 1) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_mascotaperfil, viewGroup, false);
        } else {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_mascota, viewGroup, false);
        }



        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MascotaViewHolder mascotaViewHolder, int i) {
        Mascota masconta = mascotas.get(i);
//        mascotaViewHolder.imgFoto.setImageResource(contacto.getFoto() );
        mascotaViewHolder.foto.setImageResource(masconta.getImagen());
        mascotaViewHolder.nombre.setText(masconta.getNombre());

    }

    @Override
    public int getItemCount() { //Cantidad de elementos que contiene mi lista
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre;
        private ImageView foto;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            foto = (ImageView) itemView.findViewById(R.id.imgFoto);
        }
    }
}

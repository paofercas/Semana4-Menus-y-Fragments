package com.paocas.mascotas.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.paocas.mascotas.MainActivity;
import com.paocas.mascotas.R;
import com.paocas.mascotas.fragments.PerfilMascotaFragment;
import com.paocas.mascotas.pojo.Mascota;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>
    implements View.OnClickListener{

    ArrayList<Mascota> mascotas;
    Activity activity;
    private View.OnClickListener listener;
    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas= mascotas;
        this.activity = activity;
    }

    //Inflar el layout y lo pasará al viewholder para que obtenga los elementos(views
    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        v.setOnClickListener(this);
        return  new MascotaViewHolder(v);
    }
    //Asocia cada elemento de la lista con cada view

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder holder, int position) {
        Mascota mascota= mascotas.get(position);
        holder.imgMascota.setImageResource(mascota.getFoto());
        holder.imgMascota.setBackgroundColor(mascota.getColorbackground());
        holder.txtnombre.setText(mascota.getNombre());
        holder.txttotalLike.setText(Integer.toString(mascota.getNumerolikes()));

        holder.btnlikeMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mascota.sumarLikes();
                Toast.makeText(activity,"Diste like a "+mascota.getNombre()+" y tiene "+mascota.getNumerolikes()+ " likes",Toast.LENGTH_SHORT).show();
                holder.txttotalLike.setText(Integer.toString(mascota.getNumerolikes()));
                mascota.setIslike(true);
            }
        });
    }

    @Override
    public int getItemCount() { //Cantidad de elementos que contiene mi lista
        return mascotas.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMascota;
        private Button btnlikeMascota;
        private TextView txtnombre;
        private TextView txttotalLike;
        //private Button btnLike;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMascota  = (ImageView) itemView.findViewById(R.id.imgMascota);
            btnlikeMascota  = (Button) itemView.findViewById(R.id.btnLikeMascota);
            txtnombre   = (TextView) itemView.findViewById(R.id.txtNombre);
            txttotalLike   = (TextView) itemView.findViewById(R.id.txtTotalLike);
            //btnLike         = (Button) itemView.findViewById(R.id.btnLike);
        }
    }
}

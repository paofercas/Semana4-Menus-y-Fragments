package com.paocas.mascotas.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paocas.mascotas.R;
import com.paocas.mascotas.pojo.Mascota;

import java.util.ArrayList;

public class MascotaDetailAdapter extends RecyclerView.Adapter<MascotaDetailAdapter.MascotaDetailViewHolder>
        implements View.OnClickListener{

    ArrayList<Mascota> mascotas;
    Activity activity;
    private View.OnClickListener listener;
    public MascotaDetailAdapter(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas= mascotas;
        this.activity = activity;
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

    //Inflar el layout y lo pasará al viewholder para que obtenga los elementos(views)
    @Override
    public MascotaDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascotadetail,parent,false);
        v.setOnClickListener(this);
        return  new MascotaDetailViewHolder(v);
    }

    //Asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(@NonNull MascotaDetailViewHolder holder, int position) {
        Mascota mascota= mascotas.get(position);
        holder.imgMascota.setImageResource(mascota.getFoto());
        holder.imgMascota.setBackgroundColor(mascota.getColorbackground());
        holder.txttotalLike.setText(Integer.toString(mascota.getNumerolikes()));
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }


    public static class MascotaDetailViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMascota;
        private TextView txttotalLike;

        public MascotaDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMascota  = (ImageView) itemView.findViewById(R.id.imgMascota);
            txttotalLike   = (TextView) itemView.findViewById(R.id.txtTotalLike);
        }
    }
}

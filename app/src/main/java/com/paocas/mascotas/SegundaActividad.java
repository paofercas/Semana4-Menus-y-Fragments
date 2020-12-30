package com.paocas.mascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.paocas.mascotas.adapter.MascotaAdaptador;
import com.paocas.mascotas.pojo.Mascota;

import java.util.ArrayList;

public class SegundaActividad extends AppCompatActivity {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_actividad);
        //Para que aparezca la flecha de ir atrás
        Toolbar myToolbar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView mTitle = (TextView) myToolbar.findViewById(R.id.toolbar_title);
        //tomar parametros

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas1);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
        inicializarListaMascotas();
        inicializarAdaptador();
    }
    public void inicializarAdaptador(){
        MascotaAdaptador adaptador= new MascotaAdaptador(mascotas,this);
        listaMascotas.setAdapter(adaptador);
    }
    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota( 1, "Canito", R.drawable.dog2,9,getResources().getColor(R.color.amarillo)));
        mascotas.add(new Mascota( 3, "Rex", R.drawable.dog4,6,getResources().getColor(R.color.verde)));
        mascotas.add(new Mascota( 4, "Firulais", R.drawable.dog7,5,getResources().getColor(R.color.celeste)));
        mascotas.add(new Mascota( 2, "Chompi", R.drawable.dog3,3,getResources().getColor(R.color.azul)));
        mascotas.add(new Mascota( 5, "Max", R.drawable.dog6,2,getResources().getColor(R.color.naranja)));
    }
}
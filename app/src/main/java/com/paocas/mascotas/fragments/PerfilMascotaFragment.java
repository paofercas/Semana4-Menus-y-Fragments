package com.paocas.mascotas.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.paocas.mascotas.R;
import com.paocas.mascotas.adapter.MascotaAdaptador;
import com.paocas.mascotas.adapter.MascotaDetailAdapter;
import com.paocas.mascotas.pojo.Mascota;

import java.util.ArrayList;


public class PerfilMascotaFragment extends Fragment {
    ImageView imgRedonda;
    TextView tvNombreMascota;
    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMiMascota;
    String name;
    int imagen;
    int background;
    View v;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                name= result.getString("nombre");
                imagen = result.getInt("imagen");
                background= result.getInt("background");
                tvNombreMascota.setText(name);
                imgRedonda.setImageResource(imagen);
                redondearImagen();
                inicializarListaMascotas();
                inicializarAdaptador();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_perfil_mascota, container, false);
        agregarFAB();
        listaMiMascota = (RecyclerView) v.findViewById(R.id.rvMascotas2);
        tvNombreMascota = (TextView) v.findViewById(R.id.tvNombreMascota);
        imgRedonda = (ImageView) v.findViewById(R.id.imgRedonda);

       GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
       //glm.setOrientation(LinearLayoutManager.VERTICAL);
       listaMiMascota.setLayoutManager(glm);
       return v;
    }

    public void inicializarAdaptador(){
        MascotaDetailAdapter adaptador= new MascotaDetailAdapter(mascotas,getActivity());
        listaMiMascota.setAdapter(adaptador);
    }
    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota( 1, name, imagen ,8,background));
        mascotas.add(new Mascota( 2, name, imagen,3,background));
        mascotas.add(new Mascota( 3, name , imagen,5,background));
        mascotas.add(new Mascota( 4, name, imagen,4,background));
        mascotas.add(new Mascota( 5, name, imagen,2,background));
        mascotas.add(new Mascota( 6, name, imagen,6,background));
    }

    public void agregarFAB(){
        FloatingActionButton miFAB = (FloatingActionButton) v.findViewById(R.id.fabMiFAB);
        miFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), getResources().getString(R.string.mensaje),Toast.LENGTH_SHORT).show();
                Snackbar.make(v,getResources().getString(R.string.mensaje),Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void redondearImagen(){
        Drawable originalDrawable = getResources().getDrawable(imagen);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        imgRedonda.setImageDrawable(roundedDrawable);
    }
}
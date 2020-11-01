package com.heklai.mercallery_pro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdaptadorPersona extends ArrayAdapter<Persona> {
    Context context;
    List<Persona> arraylistPersona;
    public AdaptadorPersona(@NonNull Context context, List<Persona> arraylistPersona) {
        super(context, R.layout.my_list_itempersona,arraylistPersona );

        this.context= context;
        this.arraylistPersona= arraylistPersona;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_itempersona, null, true );
        TextView idPersona= view.findViewById(R.id.idPersona);
        TextView nombre= view.findViewById(R.id.nombre);
        TextView tipo= view.findViewById(R.id.tipoPersona);

        idPersona.setText(arraylistPersona.get(position).getIdPersona());
        nombre.setText(arraylistPersona.get(position).getNombre());
        if (arraylistPersona.get(position).getIdTipo().equals("2")){
            tipo.setText("Artista");
        } else {
            tipo.setText("Cliente");
        }

        return view;
    }
}

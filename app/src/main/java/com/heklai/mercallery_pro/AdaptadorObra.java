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

public class AdaptadorObra extends ArrayAdapter<Obra> {

    Context context;
    List<Obra> arraylistObra;

    public AdaptadorObra(@NonNull Context context, List<Obra> arraylistObra) {
        super(context, R.layout.my_list_itemobra, arraylistObra);

        this.context = context;
        this.arraylistObra = arraylistObra;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_itemobra, null, true);
        TextView idObra = view.findViewById(R.id.idObra);
        TextView nombre = view.findViewById(R.id.nombreO);
        TextView precio = view.findViewById(R.id.precio);

        idObra.setText(arraylistObra.get(position).getIdPieza());
        nombre.setText(arraylistObra.get(position).getNombre());
        precio.setText(arraylistObra.get(position).getPrecio());

        return view;
    }
}
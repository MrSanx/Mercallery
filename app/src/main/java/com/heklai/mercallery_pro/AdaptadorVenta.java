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

public class AdaptadorVenta extends ArrayAdapter<Venta> {
    Context context;
    List<Venta> arraylistVenta;
    public AdaptadorVenta(@NonNull Context context, List<Venta> arraylistVenta) {
        super(context, R.layout.my_list_itemventa,arraylistVenta );

        this.context= context;
        this.arraylistVenta= arraylistVenta;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_itemventa, null, true );
        TextView idVenta= view.findViewById(R.id.idVenta);
        TextView idPieza= view.findViewById(R.id.tipoPersona);
        TextView idPersona= view.findViewById(R.id.nombre);
        TextView fechaVenta= view.findViewById(R.id.nombre2);

        idVenta.setText("ID Venta: "+arraylistVenta.get(position).getIdVenta());
        idPieza.setText("ID Pieza: "+arraylistVenta.get(position).getIdPieza());
        idPersona.setText("ID Cliente: "+arraylistVenta.get(position).getIdPersona());
        fechaVenta.setText("Fecha de Venta: "+arraylistVenta.get(position).getIdPersona());


        return view;
    }

}

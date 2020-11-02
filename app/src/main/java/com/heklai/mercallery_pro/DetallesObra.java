package com.heklai.mercallery_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetallesObra extends AppCompatActivity {

    TextView idPieza,idPersona,nombre,precio,fecha,estado;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_obra);

        idPieza= findViewById(R.id.idObra);
        idPersona= findViewById(R.id.idPersona);
        nombre= findViewById(R.id.nombre);
        precio= findViewById(R.id.precio);
        fecha= findViewById(R.id.fechaR);
        estado= findViewById(R.id.estado);

        Intent intent= getIntent();
        position= intent.getExtras().getInt("position");

        idPieza.setText("ID Obra: "+VerObra.obras.get(position).getIdPieza());
        idPersona.setText("ID Artista: "+VerObra.obras.get(position).getIdPersona());
        nombre.setText("Nombre Obra: "+VerObra.obras.get(position).getNombre());
        precio.setText("Precio: "+VerObra.obras.get(position).getPrecio());
        fecha.setText("Fecha: "+VerObra.obras.get(position).getFechaRealizacion());
        estado.setText("Estado: "+VerObra.obras.get(position).getEstado());


    }


}
package com.heklai.mercallery_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetallesPersona extends AppCompatActivity {


    TextView idPersona, idTipo, documento, nombre, fecha;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_persona);

        idPersona = findViewById(R.id.idPersona);
        idTipo = findViewById(R.id.idTipo);
        documento = findViewById(R.id.DocumentoP);
        nombre = findViewById(R.id.nombre);
        fecha = findViewById(R.id.fechaN);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        idPersona.setText("ID: " + VerPersonas.personas.get(position).getIdPersona());
        if (VerPersonas.personas.get(position).getIdTipo().equals("2")) {
            idTipo.setText("Artista ");
        } else {
            idTipo.setText("Cliente ");
        }
        documento.setText("Documento: " + VerPersonas.personas.get(position).getDocumento());
        nombre.setText("Nombre: " + VerPersonas.personas.get(position).getNombre());
        fecha.setText("Fecha: " + VerPersonas.personas.get(position).getFechaNacimiento());
    }
}
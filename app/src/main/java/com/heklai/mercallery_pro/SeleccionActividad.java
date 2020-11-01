package com.heklai.mercallery_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SeleccionActividad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_actividad);
    }
    //Metodo boton crear
    public void Crear(View view){
        Intent crear = new Intent(this, ActivityCrear.class);
        startActivity(crear);
    }

    //Metodo boton ver
    public void Ver(View view){
        Intent ver = new Intent(this, VerPersonas.class);
        startActivity(ver);
    }
}
package com.heklai.mercallery_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityVer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
    }
    //Metodo siguiente
    public void siguientePersona(View view){
        Intent siguiente = new Intent(this, VerPersonas.class);
        startActivity(siguiente);
    }
    public void siguienteObra(View view){
        Intent siguientito = new Intent(this, VerObra.class);
        startActivity(siguientito);
    }
    public void siguienteVerVenta(View view){
        Intent dalenext = new Intent(this, VerVenta.class);
        startActivity(dalenext);
    }
}
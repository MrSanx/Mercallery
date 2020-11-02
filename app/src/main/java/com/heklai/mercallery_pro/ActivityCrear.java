package com.heklai.mercallery_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityCrear extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
    }
    //Metodo siguiente
    public void siguiente(View view){
        Intent next = new Intent(this, InsertarPersona.class);
        startActivity(next);
    }
    public void siguienteObra(View view){
        Intent siguientito = new Intent(this, InsertarObra.class);
        startActivity(siguientito);
    }
    public void siguienteVenta(View view){
        Intent dale = new Intent(this, InsertarVenta.class);
        startActivity(dale);
    }
}

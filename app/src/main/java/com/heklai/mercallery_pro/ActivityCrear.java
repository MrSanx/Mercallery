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
}
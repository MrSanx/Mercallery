package com.heklai.mercallery_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InsertarPersona extends AppCompatActivity {

    EditText nombrePersona,documentoP;
    CalendarView fechaN;
    Spinner dropdown;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_persona);

        dropdown = findViewById(R.id.tipoPersona);
        String[] items = new String[]{"Cliente","Artista"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        nombrePersona= findViewById(R.id.NombreP);
        documentoP = findViewById(R.id.DocumentoP);
        fechaN= findViewById(R.id.fechaN);


        registrar= findViewById(R.id.registrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertar();
            }
        });


    }

    private void insertar() {
        fechaN= findViewById(R.id.fechaN);
        String idTipo="1";
        String nombre= nombrePersona.getText().toString().trim();
        String documento= documentoP.getText().toString().trim();

        String tipo= dropdown.getSelectedItem().toString();
        if(tipo == "Cliente"){
            idTipo= "1";
        }else if(tipo == "Artista") {
            idTipo= "2";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(new Date(fechaN.getDate()));

        ProgressDialog progressDialog= new ProgressDialog(this);
        if(nombre.isEmpty()){
            nombrePersona.setError("Complete todos los campos");
        } else if(documento.isEmpty()){
            documentoP.setError("Complete todos los campos");
        }else{
            progressDialog.show();
            String finalIdTipo = idTipo;
            StringRequest request= new StringRequest(Request.Method.POST, "https://galeriabd.000webhostapp.com/crud/conexionDB.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos Insertados")) {
                        Toast.makeText(InsertarPersona.this, "Datos Ingresados", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(InsertarPersona.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(InsertarPersona.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>params= new HashMap<String, String>();
                    params.put("tipo", finalIdTipo);
                    params.put("documento",documento);
                    params.put("nombre",nombre);
                    params.put("tipo", finalIdTipo);
                    params.put("fechaNacimiento", fecha);

                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(InsertarPersona.this);
            requestQueue.add(request);


        }

    }
}
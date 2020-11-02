package com.heklai.mercallery_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class InsertarVenta extends AppCompatActivity {

    TextView idOb,nombreOb,idCli,nombreCli,fechaN;
    Button obra,cliente,venta,fechaO;
    Calendar c;
    DatePickerDialog dpd;
    static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_venta);

        idOb = findViewById(R.id.idOb);
        nombreOb= findViewById(R.id.nombreOb);
        idCli= findViewById(R.id.idCli);
        nombreCli= findViewById(R.id.nombreCli);
        fechaN=findViewById(R.id.fechaTexto);

        fechaO= findViewById(R.id.fecha);
        fechaO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c= Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year= c.get(Calendar.YEAR);

                dpd= new DatePickerDialog(InsertarVenta.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear , int mMonth, int mDay ) {
                        fechaN.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                    }
                },day,month,year);
                dpd.show();
            }
        });



        obra= findViewById(R.id.obraV);
        obra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(InsertarVenta.this, VerObraVenta.class),1);
            }
        });
        cliente= findViewById(R.id.cliente);
        cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(InsertarVenta.this, VerCliente.class),2);
            }
        });
        venta= findViewById(R.id.venta);
        venta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertar();
                actualizar();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                int position= data.getExtras().getInt("position");
                idOb.setText(VerObraVenta.obras.get(position).getIdPieza());
                nombreOb.setText(VerObraVenta.obras.get(position).getNombre());

            }
            if(resultCode == RESULT_CANCELED){
                Toast.makeText(InsertarVenta.this,"Error al Seleccionar la Pieza", Toast.LENGTH_LONG).show();;
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {

                int position= data.getExtras().getInt("position");
                idCli.setText(VerCliente.personas.get(position).getIdPersona());
                nombreCli.setText(VerCliente.personas.get(position).getNombre());

            }
            if(resultCode == RESULT_CANCELED){
                Toast.makeText(InsertarVenta.this,"Error al Seleccionar Artista", Toast.LENGTH_LONG).show();;
            }
        }
    }

    private void insertar() {

        String idPersona= idCli.getText().toString().trim();
        String idPieza= idOb.getText().toString().trim();
        String fechaVenta= fechaN.getText().toString().trim();

        ProgressDialog progressDialog= new ProgressDialog(this);

        progressDialog.show();

        StringRequest request= new StringRequest(Request.Method.POST, "https://galeriabd.000webhostapp.com/crud/conexionDBVenta.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("Datos Insertados")) {
                    Toast.makeText(InsertarVenta.this, "Datos Ingresados", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(InsertarVenta.this, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InsertarVenta.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params= new HashMap<String, String>();

                params.put("idPersona", idPersona);
                params.put("idPieza", idPieza);
                params.put("fechaVenta", fechaVenta);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(InsertarVenta.this);
        requestQueue.add(request);


    }

    private void actualizar() {
        final String idP= idOb.getText().toString().trim();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        StringRequest request= new StringRequest(Request.Method.POST, "https://galeriabd.000webhostapp.com/crud/actualizarObraVenta.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("Datos Insertados")) {
                    Toast.makeText(InsertarVenta.this, "Datos Ingresados", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(InsertarVenta.this, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InsertarVenta.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params= new HashMap<String, String>();

                params.put("idPieza", idP);

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(InsertarVenta.this);
        requestQueue.add(request);


    }
}
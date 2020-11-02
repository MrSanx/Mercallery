package com.heklai.mercallery_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

public class EditarObra extends AppCompatActivity {
    EditText nombreObra,precioObra;
    TextView fechaN, id, nombreArt, idPieza;
    Button registrar, fechaO, artistaGet;
    Spinner estado, persona;
    int position;
    Calendar c;
    DatePickerDialog dpd;
    static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_obra);

        estado = findViewById(R.id.estadoObra);
        String[] items = new String[]{"En Exposicion","Almacen","Vendida"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        estado.setAdapter(adapter);

        idPieza= findViewById(R.id.idPieza);
        nombreObra= findViewById(R.id.NombreP);
        precioObra = findViewById(R.id.precio);
        fechaN= findViewById(R.id.fechaTexto);
        id= findViewById(R.id.id);
        nombreArt= findViewById(R.id.nombreArt);

        fechaO= findViewById(R.id.fecha);
        fechaO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c= Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year= c.get(Calendar.YEAR);

                dpd= new DatePickerDialog(EditarObra.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear , int mMonth, int mDay ) {
                        fechaN.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                    }
                },day,month,year);
                dpd.show();
            }
        });

        registrar= findViewById(R.id.registrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar();
            }
        });
        artistaGet= findViewById(R.id.verArtista);
        artistaGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(EditarObra.this, VerArtista.class),REQUEST_CODE);
            }
        });

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        idPieza.setText(VerObra.obras.get(position).getIdPieza());
        nombreObra.setText(VerObra.obras.get(position).getNombre());
        precioObra.setText(VerObra.obras.get(position).getPrecio());
        fechaN.setText(VerObra.obras.get(position).getFechaRealizacion());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                int position= data.getExtras().getInt("position");
                id.setText(VerArtista.personas.get(position).getIdPersona());
                nombreArt.setText(VerArtista.personas.get(position).getNombre());

            }
            if(resultCode == RESULT_CANCELED){
                Toast.makeText(EditarObra.this,"Error al Selccionar Artista", Toast.LENGTH_LONG).show();;
            }
        }
    }

    private void actualizar() {
        final String idP= idPieza.getText().toString().trim();
        final String idPersona= id.getText().toString().trim();
        final String nombre= nombreObra.getText().toString().trim();
        final String precioO= precioObra.getText().toString().trim();
        final String fecha = fechaN.getText().toString().trim();
        final String estadoO= estado.getSelectedItem().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        StringRequest request= new StringRequest(Request.Method.POST, "https://galeriabd.000webhostapp.com/crud/actualizarObra.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("Datos Insertados")) {
                    Toast.makeText(EditarObra.this, "Datos Ingresados", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(EditarObra.this, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditarObra.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params= new HashMap<String, String>();

                params.put("idPieza", idP);
                params.put("idPersona", idPersona);
                params.put("nombre", nombre);
                params.put("precio", precioO);
                params.put("fechaRealizacion", fecha);
                params.put("estado", estadoO);

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(EditarObra.this);
        requestQueue.add(request);


    }

}

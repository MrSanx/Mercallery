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

public class EditarPersona extends AppCompatActivity {

    EditText nombrePersona, documentoP;
    TextView fechaN, idPersona;
    Spinner dropdown;
    Button modificar, fechaO;
    Calendar c;
    DatePickerDialog dpd;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_persona);

        dropdown = findViewById(R.id.tipoPersona);
        String[] items = new String[]{"Cliente", "Artista"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        idPersona = findViewById(R.id.idPersona);
        nombrePersona = findViewById(R.id.NombreP);
        documentoP = findViewById(R.id.DocumentoP);
        fechaN = findViewById(R.id.fechaTexto);
        fechaO = findViewById(R.id.fecha);
        fechaO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(EditarPersona.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        fechaN.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                    }
                }, day, month, year);
                dpd.show();
            }
        });

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        idPersona.setText(VerPersonas.personas.get(position).getIdPersona());
        nombrePersona.setText(VerPersonas.personas.get(position).getNombre());
        documentoP.setText(VerPersonas.personas.get(position).getDocumento());
        fechaN.setText(VerPersonas.personas.get(position).getFechaNacimiento());

    }

    public void actualizar(View view) {

        String idTipo = "1";
        final String id = idPersona.getText().toString();
        final String nombre = nombrePersona.getText().toString();
        final String documento = documentoP.getText().toString();
        final String fecha = fechaN.getText().toString();
        final String tipe = dropdown.getSelectedItem().toString();
        if (tipe == "Cliente") {
            idTipo = "1";
        } else if (tipe == "Artista") {
            idTipo = "2";
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        String finalIdTipo = idTipo;
        StringRequest request = new StringRequest(Request.Method.POST, "https://galeriabd.000webhostapp.com/crud/actualizarPersona.php"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditarPersona.this, response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), VerPersonas.class));
                finish();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditarPersona.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("idPersona", id);
                params.put("idTipo", finalIdTipo);
                params.put("documento", documento);
                params.put("nombre", nombre);
                params.put("fechaNacimiento", fecha);

                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(EditarPersona.this);
        requestQueue.add(request);

    }
}
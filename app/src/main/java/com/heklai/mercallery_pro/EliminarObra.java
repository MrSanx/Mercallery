package com.heklai.mercallery_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EliminarObra extends AppCompatActivity {
    int position;
    TextView idObra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_obra);
        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        idObra= findViewById(R.id.idObraEliminar);
        idObra.setText(VerObra.obras.get(position).getIdPieza());
        final String id = idObra.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, "https://galeriabd.000webhostapp.com/crud/eliminarPieza.php"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EliminarObra.this, response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), VerPersonas.class));
                finish();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EliminarObra.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("idPieza", id);

                return params;
            }

        };;

        RequestQueue requestQueue= Volley.newRequestQueue(EliminarObra.this);
        requestQueue.add(request);

    }
}
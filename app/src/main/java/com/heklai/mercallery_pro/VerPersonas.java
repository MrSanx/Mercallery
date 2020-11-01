package com.heklai.mercallery_pro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VerPersonas extends AppCompatActivity {
    private ListView list;
    AdaptadorPersona adaptadorPersona;
    public static ArrayList<Persona> personas= new ArrayList<>();
    String url= "https://galeriabd.000webhostapp.com/crud/mostrarPersonas.php";
    Persona person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_personas);

        list= findViewById(R.id.ListViewPersonas);
        adaptadorPersona= new AdaptadorPersona(this,personas);
        list.setAdapter(adaptadorPersona);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AlertDialog.Builder builder= new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog= new ProgressDialog(view.getContext());

                CharSequence[] dialogItem= {"Ver Datos", "Eliminar Datos","A"};
                builder.setTitle(personas.get(position).getNombre());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),DetallesPersona.class).putExtra("position",position));

                                break;
                            case 1:
                                break;
                            case 2:
                                break;

                        }
                    }
                });
                builder.create().show();
            }
        });
        muestraPersonas();
    }

    public void muestraPersonas() {

        StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                personas.clear();
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String success= jsonObject.getString("success");

                    JSONArray jsonArray= jsonObject.getJSONArray("Persona");

                    if (success.equals("1")){
                        for (int i =0;i<jsonArray.length();i++){

                            JSONObject object= jsonArray.getJSONObject(i);
                            String idPersona= object.getString("idPersona");
                            String idTipo= object.getString("idTipo");
                            String documento= object.getString("documento");
                            String nombre= object.getString("nombre");
                            String fechaNacimiento= object.getString("fechaNacimiento");

                            person=new Persona(idPersona,idTipo,documento,nombre,fechaNacimiento);
                            personas.add(person);
                            adaptadorPersona.notifyDataSetChanged();

                        }
                    }

                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VerPersonas.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}
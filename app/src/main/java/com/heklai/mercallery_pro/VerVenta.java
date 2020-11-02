package com.heklai.mercallery_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class VerVenta extends AppCompatActivity {

    private ListView listVenta;
    AdaptadorVenta adaptadorVenta;
    public static ArrayList<Venta> ventas= new ArrayList<>();
    String url= "https://galeriabd.000webhostapp.com/crud/mostrarVentas.php";
    Venta vent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_venta);

        listVenta= findViewById(R.id.ListViewVentas);
        adaptadorVenta= new AdaptadorVenta(this,ventas);
        listVenta.setAdapter(adaptadorVenta);

        muestraVentas();
    }

    public void muestraVentas() {

        StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ventas.clear();
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String success= jsonObject.getString("success");

                    JSONArray jsonArray= jsonObject.getJSONArray("Ventas");

                    if (success.equals("1")){
                        for (int i =0;i<jsonArray.length();i++){

                            JSONObject object= jsonArray.getJSONObject(i);
                            String idVenta= object.getString("idVenta");
                            String idPieza= object.getString("idPieza");
                            String idPersona= object.getString("idPersona");
                            String fechaVenta= object.getString("fechaVenta");


                            vent=new Venta(idVenta,idPieza,idPersona,fechaVenta);
                            ventas.add(vent);
                            adaptadorVenta.notifyDataSetChanged();

                        }
                    }

                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VerVenta.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}
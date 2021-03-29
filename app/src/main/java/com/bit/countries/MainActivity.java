package com.bit.countries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    List<country> data = new ArrayList<>();
    LinearLayoutManager manager;
    CountryDatabase database;
    DataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recycler);
        database = CountryDatabase.getInstance(this);
        data = database.countryDao().getAll();
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new DataAdapter(data, MainActivity.this);
        recyclerView.setAdapter(adapter);
        database.countryDao().DeleteAll();
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        final String url ="https://restcountries.eu/rest/v2/all";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int index=0;index<response.length();index++) {
                    try{
                        JSONObject object = response.getJSONObject(index);
                        JSONArray languages = object.getJSONArray("languages");
                        String lang="";
                        for(int i=0;i<languages.length();i++){
                            JSONObject object1 = languages.getJSONObject(i);
                            lang+= object1.getString("name");
                            if(i<languages.length()-1){
                                lang+=",";
                            }
                        }
                        JSONArray array = object.getJSONArray("borders");
                        List<String> exampleList = new ArrayList<String>();
                        for (int i = 0; i < array.length(); i++) {
                            exampleList.add(array.getString(i));
                        }
                        String borders="";
                        int size = exampleList.size();
                        String[] stringArray  = exampleList.toArray(new String[size]);
                        for (int i=0;i<stringArray.length;i++) {
                            borders+=stringArray[i];
                            if(i<stringArray.length-1){
                                borders+=" , ";
                            }
                        }
                        String pop = NumberFormat.getNumberInstance(Locale.US).format(object.getInt("population"));
                        country country = new country(object.getString("name"),object.getString("capital"),
                                object.getString("flag"),object.getString("region"),
                                object.getString("subregion"),pop,
                                lang,borders);
                        database.countryDao().insert(country);
                        data = database.countryDao().getAll();
                        adapter = new DataAdapter(data, MainActivity.this);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, "JSON Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);

    }
}
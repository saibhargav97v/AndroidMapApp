package appdevtask1.bhargav.androidstudiomapapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.OnItemClickListener;


public class MainActivity2 extends AppCompatActivity implements AsyncResponse {
    RequestTask asynctask = new RequestTask();
    ListView list;
    String category;
    String s1="https://spider.nitt.edu/lateral/appdev/coordinates?category=";
    List<String> hostel_name = new ArrayList<String>();
    List<String> lat = new ArrayList<String>();
    List<String> lng = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        asynctask.delegate = this;
        //Toast.makeText(getApplicationContext(),"Value is" + value1 ,Toast.LENGTH_LONG).show();
        category=getIntent().getStringExtra("categ");
        asynctask.execute(s1 + category);
        list = (ListView) findViewById(R.id.listView);

    }

    @Override
    public void processFinish(String output) {
        Log.d("debugTag", "this is process finish:" + output);
        try {
            JSONArray hostels = new JSONArray(output);
            for( int i = 0;i <hostels.length() ;i++)
            {
                JSONObject hostel_object = hostels.getJSONObject(i);
                Log.d("debugTag",hostel_object.toString());
                Log.d("debugTag",hostel_object.optString("name"));
                hostel_name.add(hostel_object.optString("name"));
                lat.add(hostel_object.optString("latitude"));
                lng.add(hostel_object.optString("longitude"));
            }

            for(String item:hostel_name)
            {
                Log.d("debugTag",item);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hostel_name);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Intent i = new Intent(MainActivity2.this, MapsActivity.class);
                    i.putExtra("value", hostel_name.get(position));
                    i.putExtra("Map1", lat.get(position));
                    i.putExtra("Map2", lng.get(position));
                    startActivity(i);

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}

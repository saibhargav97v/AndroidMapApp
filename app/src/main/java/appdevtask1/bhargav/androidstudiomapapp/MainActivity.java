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

class RequestTask extends AsyncTask<String, String, StringBuffer> {

        public AsyncResponse delegate = null;
    @Override
    protected StringBuffer doInBackground(String... uri) {
        String link = "https://spider.nitt.edu/lateral/appdev/coordinates?category=hostels";
        URL url = null;

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuffer buffer = null;
        try {
            Log.d("debugTag"," In background");
            url = new URL(link);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            buffer = new StringBuffer();

            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return buffer;
    }

    @Override
    protected void onPostExecute(StringBuffer result) {
        super.onPostExecute(result);
        //Do anything with response..
        delegate.processFinish(result.toString());


        //Log.d("debugTag" , result.toString());
    }
}

    public class MainActivity extends AppCompatActivity implements AsyncResponse {
    RequestTask asynctask = new RequestTask();
    ListView list;
     //String contacts[]={"Amber","Garnet","Zircon","Opal"};
        List<String> hostel_name = new ArrayList<String>();
        List<String> lat = new ArrayList<String>();
        List<String> lng = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asynctask.delegate = this;
        //Toast.makeText(getApplicationContext(),"Value is" + value1 ,Toast.LENGTH_LONG).show();
        asynctask.execute("https://spider.nitt.edu/lateral/appdev/coordinates?category=hostels");
        list = (ListView) findViewById(R.id.listView);

    }

        @Override
        public void processFinish(String output) {
            Log.d("debugTag","this is process finish:"+output);
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


                            Intent i = new Intent(MainActivity.this, MapsActivity.class);
                            i.putExtra("Map1", lat.get(position));
                            i.putExtra("Map2", lng.get(position));
                            startActivity(i);
                        /* else if (list.getItemIdAtPosition(position) == 1) {

                            Intent i = new Intent(MainActivity.this, MapsActivity.class);
                            i.putExtra("Map1", "10.762201");
                            i.putExtra("Map2", "78.811058");
                            startActivity(i);
                            // Toast.makeText(getApplicationContext(), " " + list.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
                        } else if (list.getItemIdAtPosition(position) == 2) {

                            Intent i = new Intent(MainActivity.this, MapsActivity.class);
                            i.putExtra("Map1", "10.766417");
                            i.putExtra("Map2", "78.817302");
                            startActivity(i);
                            // Toast.makeText(getApplicationContext(), " " + list.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
                        } else if (list.getItemIdAtPosition(position) == 3) {

                            Intent i = new Intent(MainActivity.this, MapsActivity.class);
                            i.putExtra("Map1", "10.767068");
                            i.putExtra("Map2", "78.821776");
                            startActivity(i);
                            // Toast.makeText(getApplicationContext(), " " + list.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}

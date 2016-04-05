package appdevtask1.bhargav.androidstudiomapapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lenvopc on 05-04-2016.
 */
class RequestTask extends AsyncTask<String, String, StringBuffer> {

    public AsyncResponse delegate = null;
    @Override
    protected StringBuffer doInBackground(String... uri) {
        //String link = "https://spider.nitt.edu/lateral/appdev/coordinates?category=hostels";
        URL url = null;

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuffer buffer = null;
        try {
            Log.d("debugTag", " In background"+" " + uri[0]);
            url = new URL(uri[0]);
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


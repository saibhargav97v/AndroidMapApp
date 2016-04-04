package appdevtask1.bhargav.androidstudiomapapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends AppCompatActivity {

    ListView list;
    String contacts[]={"Amber","Garnet","Zircon","Opal"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Bundle extras = getIntent().getExtras();
        //String value1 = extras.getString("Value1");
        //Toast.makeText(getApplicationContext(),"Value is" + value1 ,Toast.LENGTH_LONG).show();

        list=(ListView)findViewById(R.id.listView);
        ArrayAdapter <String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contacts);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getApplicationContext(), " " + list.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),"Amber",Toast.LENGTH_LONG).show();
                if (list.getItemIdAtPosition(position) == 0) {
                    Intent i = new Intent(MainActivity.this, MapsActivity.class);
                    i.putExtra("Map1", "10.767355");
                    i.putExtra("Map2", "78.81344");
                    startActivity(i);
                }

                else if(list.getItemIdAtPosition(position) == 1){

                    Intent i = new Intent(MainActivity.this, MapsActivity.class);
                    i.putExtra("Map1", "10.762201");
                    i.putExtra("Map2", "78.811058");
                    startActivity(i);
                    // Toast.makeText(getApplicationContext(), " " + list.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
                }
                else if(list.getItemIdAtPosition(position) == 2){

                    Intent i = new Intent(MainActivity.this, MapsActivity.class);
                    i.putExtra("Map1", "10.766417");
                    i.putExtra("Map2", "78.817302");
                    startActivity(i);
                    // Toast.makeText(getApplicationContext(), " " + list.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
                }
                else if(list.getItemIdAtPosition(position) == 3){

                    Intent i = new Intent(MainActivity.this, MapsActivity.class);
                    i.putExtra("Map1", "10.767068");
                    i.putExtra("Map2", "78.821776");
                    startActivity(i);
                    // Toast.makeText(getApplicationContext(), " " + list.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
                }
            }
        });
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

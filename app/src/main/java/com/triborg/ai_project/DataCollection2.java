package com.triborg.ai_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataCollection2 extends AppCompatActivity {
    float pressure;
    float fingersize;
    long downtime;
    long eventtime;
    int flag=0;
    String username;
    Toast t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://en.wikipedia.org/wiki/Project");
        Button next=(Button) findViewById(R.id.button4);
        myWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction() & MotionEvent.ACTION_MASK;
                float touchmajor = event.getTouchMajor();
                float touchminor = event.getTouchMinor();
                // float historicaltouch=event.getHistoricalEventTime(0);
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    pressure = event.getPressure();
                    fingersize = event.getSize();
                    downtime = event.getDownTime();
                    eventtime = event.getEventTime();
                    float downtime = event.getDownTime();
                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    Intent i=getIntent();
                    username=i.getStringExtra("Username");
                    long time=event.getEventTime() - event.getDownTime();
                    Log.d("Data Collection", Float.toString(time));
                    Log.d("Data Collection", Float.toString(pressure));
                    Log.d("Data Collection", Float.toString(fingersize));
                    Log.d("Data Collection", Float.toString(downtime));
                    Log.d("Data Collection", Float.toString(eventtime));
                    Log.d("Data Collection", Float.toString(touchmajor));
                    Log.d("Data Collection", Float.toString(touchminor));
                    //  Log.d("Data Collection", Float.toString(historicaltouch));
                    Log.d("Data Collection", Float.toString(flag));
                    float x = event.getX();
                    float y = event.getY();
                   // username=name.getText().toString();
                    String values=time+","+eventtime+","+x+","+y+","+pressure+","+fingersize+","+touchmajor+","+touchminor+","+3+","+username+"\n";
                    writeToFile(values);
                    flag++;
                }
              /*  if(action==MotionEvent.ACTION_UP) {

                    pressure = event.getPressure();
                    fingersize = event.getSize();
                    downtime = event.getDownTime();
                    eventtime = event.getEventTime();
                    Log.d("Data Collection", Float.toString(pressure));
                    Log.d("Data Collection", Float.toString(fingersize));
                    float x = event.getX();
                    float y = event.getY();

                    String values=eventtime+","+x+","+y+","+pressure+","+fingersize+","+username+"\n";
                    writeToFile(values);


                }*/
                return false;
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag>5)
                {
                    Intent i=new Intent(getApplicationContext(),Main3Activity.class);
                    startActivity(i);
                }
                else
                {
                    t.makeText(DataCollection2.this, "Please Scroll through the page to continue", Toast.LENGTH_SHORT).show();
                    Log.d("Data Collection", "Inv");
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_User);

        Spinner spinner = (Spinner) item.getActionView();
        List<String> list = new ArrayList<String>();
        list.add("Owner");
        list.add("Guest");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_DataEntry) {
            Intent i=new Intent(getApplicationContext(),DataCollection.class);
            startActivity(i);
        }
        if (id == R.id.action_Authenticate) {
            Intent i=new Intent(getApplicationContext(),Main3Activity.class);
            startActivity(i);
        }
        else if (id == R.id.action_Exit) {
            finish();
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }
    private void writeToFile(String data) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "My_Data");
            if (!root.exists()) {
                root.mkdirs();
            }

            File gpxfile = new File(root, "test.csv");
            boolean exists= gpxfile.exists();
            FileWriter writer = new FileWriter(gpxfile,true);


            BufferedWriter out = new BufferedWriter(writer,MODE_APPEND);
            out.write(data);
            out.close();

            t.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}

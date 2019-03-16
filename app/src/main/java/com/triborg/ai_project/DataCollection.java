package com.triborg.ai_project;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataCollection extends AppCompatActivity {

    float pressure;
    float fingersize;
    long downtime;
    long eventtime;
    int flag=0;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setTitle(R.string.action_DataEntry);
        }
        Button button1=(Button) findViewById(R.id.button);
        Button button2=(Button) findViewById(R.id.button2);
        Button next=(Button) findViewById(R.id.button3);
        final Switch Switch1=(Switch) findViewById(R.id.switch1);
        final Switch Switch2=(Switch) findViewById(R.id.switch2);
        final SeekBar Seek1=(SeekBar) findViewById(R.id.seekBar);
        final EditText name=(EditText) findViewById(R.id.editText);
      // final DBHelper dbEntry=new DBHelper(this);
        Seek1.setMax(100);
        Seek1.setProgress(0);
        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(flag==0) {

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
                        username=name.getText().toString();
                        if(username != null && !username.trim().isEmpty()){
                            String values = time + "," + eventtime + "," + x + "," + y + "," + pressure + "," + fingersize + "," + touchmajor + "," + touchminor + ","+1+","+ username + "\n";
                            writeToFile(values);
                        }
                        else{
                            Toast.makeText(DataCollection.this, "Username is empty", Toast.LENGTH_SHORT).show();
                        }
                        flag++;
                        }

                }
                else{
                   // flag=0;
                    //Toast.makeText(DataCollection.this, "Invalid Sequence", Toast.LENGTH_SHORT).show();
                    Log.d("Data Collection", "Inv");
                }
                return false;
            }
        });
        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(flag==1) {
                    //flag++;
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
                        username=name.getText().toString();
                        if(username != null && !username.trim().isEmpty()){
                            String values = time + "," + eventtime + "," + x + "," + y + "," + pressure + "," + fingersize + "," + touchmajor + "," + touchminor + "," +1+","+ username + "\n";
                            writeToFile(values);
                        }
                        else{
                            Toast.makeText(DataCollection.this, "Username is empty", Toast.LENGTH_SHORT).show();
                        }
                        flag++;
                    }
                }
                else{
                    //flag=0;
                    //Toast.makeText(DataCollection.this, "Invalid Sequence", Toast.LENGTH_SHORT).show();
                    Log.d("Data Collection", "Inv");
                }
                return false;
            }
        });
        Switch2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(flag==2) {
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
                        username=name.getText().toString();
                        if(username != null && !username.trim().isEmpty()) {
                            String values = time + "," + eventtime + "," + x + "," + y + "," + pressure + "," + fingersize + "," + touchmajor + "," + touchminor + "," +2+","+ username + "\n";
                            writeToFile(values);
                        }
                        else{
                            Toast.makeText(DataCollection.this, "Username is empty", Toast.LENGTH_SHORT).show();
                        }
                        flag++;
                    }
                }
                else{
                    //flag=0;
                    //Toast.makeText(DataCollection.this, "Invalid Sequence", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        Switch1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(flag==3) {

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
                        username=name.getText().toString();
                        if(username != null && !username.trim().isEmpty()){
                            String values = time + "," + eventtime + "," + x + "," + y + "," + pressure + "," + fingersize + "," + touchmajor + "," + touchminor + "," +2+","+ username + "\n";
                            writeToFile(values);
                        }
                        else{
                            Toast.makeText(DataCollection.this, "Username is empty", Toast.LENGTH_SHORT).show();
                        }
                        flag++;
                    }
                }
                else{
                    //flag=0;
                    //Toast.makeText(DataCollection.this, "Invalid Sequence", Toast.LENGTH_SHORT).show();
                    Log.d("Data Collection", "Inv");
                }
                return false;
            }
        });
        /*Seek1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(flag==4) {

                    pressure = event.getPressure();
                    fingersize = event.getSize();
                    downtime = event.getDownTime();
                    eventtime = event.getEventTime();
                    Log.d("Data Collection", Float.toString(pressure));
                    Log.d("Data Collection", Float.toString(fingersize));
                }
                else{
                    Toast.makeText(DataCollection.this, "Invalid Sequence", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });*/

        /*Seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

                Toast.makeText(DataCollection.this, "Seek bar progress is :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }

        });*/
        Seek1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_MOVE) {
                    if (flag == 4) {
                        flag++;
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
                            username=name.getText().toString();
                            if(username != null && !username.trim().isEmpty()){
                                String values = time + "," + eventtime + "," + x + "," + y + "," + pressure + "," + fingersize + "," + touchmajor + "," + touchminor + "," +3+","+ username + "\n";
                                writeToFile(values);
                            }
                            else{
                                Toast.makeText(DataCollection.this, "Username is empty", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else{
                        //flag=0;
                        //Toast.makeText(DataCollection.this, "Invalid Sequence", Toast.LENGTH_SHORT).show();
                        Log.d("Data Collection", "Inv");
                    }
                }
                Log.d("Data Collection", "Touched , Progress :" + Seek1.getProgress());
                return false;
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==5)
                {
                    Intent i=new Intent(getApplicationContext(),DataCollection2.class);
                    i.putExtra("Username",name.getText().toString());
                    startActivity(i);
                }
                else{
                    flag=0;
                    Toast.makeText(DataCollection.this, "Invalid Sequence", Toast.LENGTH_SHORT).show();
                    Switch1.setChecked(false);
                    Switch2.setChecked(false);
                    Seek1.setProgress(0);
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

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}

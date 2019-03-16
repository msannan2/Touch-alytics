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
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    float pressure;
    float fingersize;
    long downtime;
    long eventtime;
    float x;
    float y;
    String temp;
    TextView Userlabel;
    KNN_Implementation trn_ds;
    RandomForestCateg RFC;
    String classifier;
    ArrayList<ArrayList<String>> predict;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button button=(Button)findViewById(R.id.button5);
        Button button1=(Button)findViewById(R.id.button6);
        Button button2=(Button)findViewById(R.id.button7);
        final Switch switch1=(Switch) findViewById(R.id.switch3);
        Button trainButton=(Button)findViewById(R.id.button8);
        Userlabel=(TextView)  findViewById(R.id.textView4);
        predict=new ArrayList<ArrayList<String>>();
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        List<String> list = new ArrayList<String>();
        list.add("KNN");
        list.add("RF");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
    trainButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            classifier=spinner.getSelectedItem().toString();
            if(classifier=="KNN")
            {
                trn_ds=new KNN_Implementation();
                trn_ds.getKValueandDistMetrics();


                try {
                    trn_ds.loadtrainData("test.csv");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                trn_ds.clearPrevData();
                Toast.makeText(Main3Activity.this, "Classifier Trained", Toast.LENGTH_SHORT).show();
            }
            else  if(classifier=="RF")
            {
                DescribeTreesCateg DT = new DescribeTreesCateg("test.csv");
                ArrayList<ArrayList<String>> Train = DT.CreateInputCateg("test.csv");
                ArrayList<ArrayList<String>> Test = DT.CreateInputCateg("test.csv");
                //for storing labels
                HashMap<String, Integer> Classes = new HashMap<String, Integer>();
                for(ArrayList<String> dp : Train){
                    String clas = dp.get(dp.size()-1);
                    if(Classes.containsKey(clas))
                        Classes.put(clas, Classes.get(clas)+1);
                    else
                        Classes.put(clas, 1);
                }

                int numTrees=100;
                int M=Train.get(0).size()-1;
                int Ms = (int)Math.round(Math.log(M)/Math.log(2)+1);
                int C = Classes.size();
                RFC = new RandomForestCateg(numTrees, M, Ms, C, Train, Test);
                RFC.Start();
                Toast.makeText(Main3Activity.this, "Classifier Trained", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(Main3Activity.this, "Please Select a Model to train", Toast.LENGTH_SHORT).show();
            }
        }
    });
        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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
                    long time = event.getEventTime() - event.getDownTime();
                    Log.d("Data Collection", Float.toString(time));
                    Log.d("Data Collection", Float.toString(pressure));
                    Log.d("Data Collection", Float.toString(fingersize));
                    Log.d("Data Collection", Float.toString(downtime));
                    Log.d("Data Collection", Float.toString(eventtime));
                    Log.d("Data Collection", Float.toString(touchmajor));
                    Log.d("Data Collection", Float.toString(touchminor));
                    //  Log.d("Data Collection", Float.toString(historicaltouch));
                    // Log.d("Data Collection", Float.toString(flag));
                    float x = event.getX();
                    float y = event.getY();
                    if(classifier=="KNN")
                    {
                        double[] test = new double[8];
                        String label = "Hello";
                        test[0]=time;
                        test[1] = eventtime;
                        test[2]=x;
                        test[3]=y;
                        test[4]=pressure;
                        test[5]=fingersize;
                        test[6]=touchmajor;
                        test[7]=touchminor;
                        test[8]=1;

                        try {
                            trn_ds.loadtestData(test , label);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                    }
                    else if(classifier=="RF")
                    {
                        ArrayList<String> attributes=new ArrayList<String>();
                        attributes.add(Long.toString(time));
                        attributes.add(Long.toString(eventtime));
                        attributes.add(String.valueOf(x));
                        attributes.add(String.valueOf(y));
                        attributes.add(String.valueOf(pressure));
                        attributes.add(String.valueOf(fingersize));
                        attributes.add(String.valueOf(touchmajor));
                        attributes.add(String.valueOf(touchminor));
                        attributes.add(String.valueOf(1));
                        predict.add(attributes);
                    }
                }

                return false;
            }
        });
        switch1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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
                    long time = event.getEventTime() - event.getDownTime();
                    Log.d("Data Collection", Float.toString(time));
                    Log.d("Data Collection", Float.toString(pressure));
                    Log.d("Data Collection", Float.toString(fingersize));
                    Log.d("Data Collection", Float.toString(downtime));
                    Log.d("Data Collection", Float.toString(eventtime));
                    Log.d("Data Collection", Float.toString(touchmajor));
                    Log.d("Data Collection", Float.toString(touchminor));
                    //  Log.d("Data Collection", Float.toString(historicaltouch));
                    // Log.d("Data Collection", Float.toString(flag));
                    float x = event.getX();
                    float y = event.getY();
                    if(classifier=="KNN")
                    {
                        double[] test = new double[9];
                        String label = "Hello";
                        test[0]=time;
                        test[1] = eventtime;
                        test[2]=x;
                        test[3]=y;
                        test[4]=pressure;
                        test[5]=fingersize;
                        test[6]=touchmajor;
                        test[7]=touchminor;
                        test[8]=2;

                        try {
                            trn_ds.loadtestData(test , label);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                    }
                    else if(classifier=="RF")
                    {
                        ArrayList<String> attributes=new ArrayList<String>();
                        attributes.add(Long.toString(time));
                        attributes.add(Long.toString(eventtime));
                        attributes.add(String.valueOf(x));
                        attributes.add(String.valueOf(y));
                        attributes.add(String.valueOf(pressure));
                        attributes.add(String.valueOf(fingersize));
                        attributes.add(String.valueOf(touchmajor));
                        attributes.add(String.valueOf(touchminor));
                        attributes.add(String.valueOf(2));
                        predict.add(attributes);
                    }
                }

                return false;
            }
        });
    button1.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
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
                long time = event.getEventTime() - event.getDownTime();
                Log.d("Data Collection", Float.toString(time));
                Log.d("Data Collection", Float.toString(pressure));
                Log.d("Data Collection", Float.toString(fingersize));
                Log.d("Data Collection", Float.toString(downtime));
                Log.d("Data Collection", Float.toString(eventtime));
                Log.d("Data Collection", Float.toString(touchmajor));
                Log.d("Data Collection", Float.toString(touchminor));
                //  Log.d("Data Collection", Float.toString(historicaltouch));
                // Log.d("Data Collection", Float.toString(flag));
                float x = event.getX();
                float y = event.getY();
                if(classifier=="KNN")
                {
                    trn_ds.clearPrevData();
                    double[] test = new double[9];
                    String label = "Hello";
                    test[0]=time;
                    test[1] = eventtime;
                    test[2]=x;
                    test[3]=y;
                    test[4]=pressure;
                    test[5]=fingersize;
                    test[6]=touchmajor;
                    test[7]=touchminor;
                    test[8]=1;

                    try {
                        trn_ds.loadtestData(test , label);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                }
                else if(classifier=="RF")
                {
                    predict.clear();
                    ArrayList<String> attributes=new ArrayList<String>();
                    attributes.add(Long.toString(time));
                    attributes.add(Long.toString(eventtime));
                    attributes.add(String.valueOf(x));
                    attributes.add(String.valueOf(y));
                    attributes.add(String.valueOf(pressure));
                    attributes.add(String.valueOf(fingersize));
                    attributes.add(String.valueOf(touchmajor));
                    attributes.add(String.valueOf(touchminor));
                    attributes.add(String.valueOf(1));
                    predict.add(attributes);
                }
            }

            return false;
        }
    });
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float touchmajor = event.getTouchMajor();
                float touchminor = event.getTouchMinor();
                switch1.setChecked(false);
                // float historicaltouch=event.getHistoricalEventTime(0);
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    pressure = event.getPressure();
                    fingersize = event.getSize();
                    downtime = event.getDownTime();
                    eventtime = event.getEventTime();
                    float downtime = event.getDownTime();
                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    long time = event.getEventTime() - event.getDownTime();
                    Log.d("Data Collection", Float.toString(time));
                    Log.d("Data Collection", Float.toString(pressure));
                    Log.d("Data Collection", Float.toString(fingersize));
                    Log.d("Data Collection", Float.toString(downtime));
                    Log.d("Data Collection", Float.toString(eventtime));
                    Log.d("Data Collection", Float.toString(touchmajor));
                    Log.d("Data Collection", Float.toString(touchminor));

                    //  Log.d("Data Collection", Float.toString(historicaltouch));
                    // Log.d("Data Collection", Float.toString(flag));
                    float x = event.getX();
                    float y = event.getY();
                    if(classifier=="KNN")
                    {
                        double[] test = new double[9];
                        String label = "Hello";
                        test[0]=time;
                        test[1] = eventtime;
                        test[2]=x;
                        test[3]=y;
                        test[4]=pressure;
                        test[5]=fingersize;
                        test[6]=touchmajor;
                        test[7]=touchminor;
                        test[8]=1;


                        try {
                            trn_ds.loadtestData(test , label);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        try {
                            temp=trn_ds.distanceCalcualte();
                            Userlabel.setText("User: "+temp);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(classifier=="RF")
                    {
                        ArrayList<String> attributes=new ArrayList<String>();
                        attributes.add(Long.toString(time));
                        attributes.add(Long.toString(eventtime));
                        attributes.add(String.valueOf(x));
                        attributes.add(String.valueOf(y));
                        attributes.add(String.valueOf(pressure));
                        attributes.add(String.valueOf(fingersize));
                        attributes.add(String.valueOf(touchmajor));
                        attributes.add(String.valueOf(touchminor));
                        attributes.add(String.valueOf(1));
                        predict.add(attributes);
                        String ret=RFC.PredictForestForLabel(predict);
                        Userlabel.setText("User: "+ret);
                        System.out.println(ret);
                    }
                }

                return false;
            }
        });
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
        if (id == R.id.action_DataEntry) {
            Intent i=new Intent(getApplicationContext(),DataCollection.class);
            startActivity(i);
        }
        //noinspection SimplifiableIfStatement
        else if (id == R.id.action_Exit) {
            finish();
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }
}

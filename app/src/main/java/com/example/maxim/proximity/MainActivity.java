package com.example.maxim.proximity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int x=0;
    boolean tren=false;


    TextView ProximitySensor;
    TextView ProximityMax;
    TextView ProximityReading;
    TextView textView;

    SensorManager mySensorManager;
    Sensor myProximitySensor;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProximitySensor = (TextView) findViewById(R.id.textView1);
        ProximityMax = (TextView) findViewById(R.id.textView2);
        ProximityReading = (TextView) findViewById(R.id.textView3);
        textView = (TextView) findViewById(R.id.textView);

        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (myProximitySensor == null) {
            ProximitySensor.setText("Датчик расстояния отсутствует!");
        } else {
            ProximitySensor.setText(myProximitySensor.getName());
            ProximityMax.setText("Максимальное расстояние: " + String.valueOf(myProximitySensor.getMaximumRange()));
            mySensorManager.registerListener(proximitySensorEventListener, myProximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    SensorEventListener proximitySensorEventListener = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                ProximityReading.setText("Считывание показаний: " + String.valueOf(event.values[0]));

                if (tren){
                    if (event.values[0]==0){
                        x++;
                        String s= String.valueOf(x);
                        textView.setText(s);
                    }

                }
            }
        }
    };

    public void st(View view) {
        if (!tren) {
            tren = true;
        } else  {
            tren = false;

        }
    }

    public void off(View view) {
        mySensorManager.unregisterListener(proximitySensorEventListener);
    }
}

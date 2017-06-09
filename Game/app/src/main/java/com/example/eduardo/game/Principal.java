package com.example.eduardo.game;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Principal extends AppCompatActivity implements SensorEventListener {
    SensorManager sensor;
    private Sensor acelerometro, proximidad,luz;
    LinearLayout ln;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        sensor=(SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro= sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proximidad= sensor.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        luz= sensor.getDefaultSensor(Sensor.TYPE_LIGHT);
        ln =(LinearLayout)findViewById(R.id.activity_principal);
    }
    public void Tibu(View v){
        Intent acceder= new Intent(this,Tiburones.class);
        startActivity(acceder);

    }
    public  void Feli(View v) {
        Intent acceder = new Intent(this, Felinos.class);
        startActivity(acceder);
    }
    public void  mascota(View v){
        Intent acceder = new Intent(this, Gatos.class);
        startActivity(acceder);

    }
    public void dibu(View v){
        Intent acceder= new Intent(this,Paint.class);
        startActivity(acceder);

    }
    @Override
    protected void onResume() {
        super.onResume();
        sensor.registerListener(this,acelerometro,SensorManager.SENSOR_DELAY_UI);
        sensor.registerListener(this,proximidad,SensorManager.SENSOR_DELAY_UI);
        sensor.registerListener(this,luz,SensorManager.SENSOR_DELAY_UI);


    }

    @Override
    protected void onPause() {
        super.onPause();
        sensor.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x,y,z;
        x= event.values[0];
        if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            if(x<20){
                ln.setBackgroundColor(Color.CYAN);
            }else if(x<40){
                ln.setBackgroundColor(Color.GREEN);

            }else if(x<60){
                ln.setBackgroundColor(Color.MAGENTA);
            }else if(x<80){
            ln.setBackgroundColor(Color.WHITE);
        }

            }

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

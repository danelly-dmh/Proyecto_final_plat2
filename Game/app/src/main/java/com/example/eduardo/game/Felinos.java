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
import android.widget.AdapterView;
import android.widget.ListView;

public class Felinos extends AppCompatActivity implements ListView.OnItemClickListener, SensorEventListener {
    ListView listtub;
    Intent inPlay;
    SensorManager sensor;
    private Sensor acelerometro, proximidad,luz;
    Datos_tiburones[] adcDatosFeli={
            new Datos_tiburones("Leon"," África y Asia. El león asiático es un animal en peligro de extinción ","Carnívoro (Zebras, gacelas, antílopes, jirafas... )","R-RIEb74IuI",R.drawable.leon,2.674140, 14.705468,-11.697535, 27.613557,21.124041, 70.824216),
            new Datos_tiburones(" Tigre","El tigre se encuentra en Asia, desde Siberia al Norte hasta la isla de Java al sur.","Es carnívoro. Ciervos, antílopes, jabalíes, etc. Mamíferos grandes","FmaDaf6Wa4E", R.drawable.tigre,43.584193, 45.000003,0.938238, 114.537525, 31.349169, 88.317610),
            new Datos_tiburones("Puma","El puma tiene una gran distribución geográfica, habitando desde Canadá hasta los Andes de América del Sur."," s un mamífero carnívoro","3MDWESW5Jew",R.drawable.pum,62.334525, -136.060649,-21.182000, -66.764591,25.954964, -107.046140),



    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_felinos);
        listtub=(ListView)findViewById(R.id.listfel);
        listtub.setAdapter(new TiburonAdapter(this,R.layout.list_tiburones,adcDatosFeli));
        sensor=(SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro= sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proximidad= sensor.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        luz= sensor.getDefaultSensor(Sensor.TYPE_LIGHT);
        listtub.setOnItemClickListener(this);
        inPlay = new Intent(this,playfeli.class);
        startService(inPlay);
    }
   /* @Override
    protected void onPause() {
        super.onPause();
        stopService(inPlay);
    }*/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent inDetalles = new Intent(this, Info.class);
        Bundle bnDatos = new Bundle();
        bnDatos.putString("Nom",adcDatosFeli[position].Nom);
        bnDatos.putString("Habi",adcDatosFeli[position].Habitat);
        bnDatos.putString("Ali",adcDatosFeli[position].Alimentacion);
        bnDatos.putString("Video",adcDatosFeli[position].Video);
        bnDatos.putInt("Img",adcDatosFeli[position].iImagen);
        bnDatos.putDouble("A1",adcDatosFeli[position].Alt);
        bnDatos.putDouble("L1",adcDatosFeli[position].Lng);
        bnDatos.putDouble("A2",adcDatosFeli[position].Alt2);
        bnDatos.putDouble("L2",adcDatosFeli[position].Lng2);
        bnDatos.putDouble("A3",adcDatosFeli[position].Alt3);
        bnDatos.putDouble("L3",adcDatosFeli[position].Lng3);

        inDetalles.putExtras(bnDatos);
        startActivity(inDetalles);

    }
    protected void onResume() {
        super.onResume();
        sensor.registerListener(this,proximidad,SensorManager.SENSOR_DELAY_UI);


    }

    @Override
    protected void onPause() {
        super.onPause();
        sensor.unregisterListener(this);
        inPlay = new Intent(this,playfeli.class);
        stopService(inPlay);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        if(event.sensor.getType()== Sensor.TYPE_PROXIMITY) {

            float x1= event.values[0];



            if (x1 ==1) {
                inPlay = new Intent(this,playfeli.class);
                stopService(inPlay);
            }



            } else {
            inPlay = new Intent(this,playfeli.class);
            startService(inPlay);


            }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    }

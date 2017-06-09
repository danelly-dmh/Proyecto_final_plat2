package com.example.eduardo.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Gatos extends AppCompatActivity implements ListView.OnItemClickListener{
    ListView listtub;
    Intent inPlay;
    Datos_tiburones[] adcDatosGatos={
            new Datos_tiburones("Gato Azul Ruso"," Es una raza natural que apareció en los alrededores del puerto de Arkhangelsk en el norte de Rusia ","Carnívoro ","bN7Z6S3Yr0",R.drawable.gatoazul,64.540030, 40.555757,64.575498, 40.514477,64.518422, 40.542235),
            new Datos_tiburones(" Gato persa","Los primeros gatos persas fueron introducidos en Italia desde Persia (actual Irán) en la década de 1620 y a sus descendientes se les llamó de muchas maneras. La rama persa actual se desarrolló a finales de 1800 en Inglaterrar.","Es carnívoro.","r8P5Lmp2JmY", R.drawable.gatper,41.842465, 12.525218,32.405280, 53.700435,52.353776, -1.424109),
            new Datos_tiburones("Perros Husky","utilizados tradicionalmente como perro de trineo1 en regiones septentrionales árticas y subárticas","Omnivoro","uRXmA10PYM0",R.drawable.husk,69.644246, 27.362958,60.776932, 7.401128,78.963626, 18.297365),
            new Datos_tiburones("Perros Malamute de Alaska","Es un perro originario de la zona ártica, una de las razas más antiguas dentro de los perros de trineo.","Omnivoro","uRXmA10PYM0",R.drawable.baha,70.319808, -43.520473,72.701304, 95.449680,65.180505, -100.536140),



    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatos);
        listtub=(ListView)findViewById(R.id.listgatos);
        listtub.setAdapter(new TiburonAdapter(this,R.layout.list_tiburones,adcDatosGatos));
        listtub.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent inDetalles = new Intent(this, Info.class);
        Bundle bnDatos = new Bundle();
        bnDatos.putString("Nom",adcDatosGatos[position].Nom);
        bnDatos.putString("Habi",adcDatosGatos[position].Habitat);
        bnDatos.putString("Ali",adcDatosGatos[position].Alimentacion);
        bnDatos.putString("Video",adcDatosGatos[position].Video);
        bnDatos.putInt("Img",adcDatosGatos[position].iImagen);
        bnDatos.putDouble("A1",adcDatosGatos[position].Alt);
        bnDatos.putDouble("L1",adcDatosGatos[position].Lng);
        bnDatos.putDouble("A2",adcDatosGatos[position].Alt2);
        bnDatos.putDouble("L2",adcDatosGatos[position].Lng2);
        bnDatos.putDouble("A3",adcDatosGatos[position].Alt3);
        bnDatos.putDouble("L3",adcDatosGatos[position].Lng3);

        inDetalles.putExtras(bnDatos);
        startActivity(inDetalles);}
}

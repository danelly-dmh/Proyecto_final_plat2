package com.example.eduardo.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Tiburones extends AppCompatActivity implements ListView.OnItemClickListener{
ListView listtub;
    Intent inPlay;
    Datos_tiburones[] adcDatosFrappe={
            new Datos_tiburones("Tiburon Blanco","Se localiza con más frecuencia en las costas de Norteamérica desde Terranova hasta el sur de México, pasando por Alaska y Florida. Fuera de América el tiburón blanco gusta principalmente de las aguas de Sudáfrica, del mar Mediterráneo, de Japón y de Oceanía.","suele alimentarse de especies grandes, sino de animales más pequeños como calamares, rayas y otros peces.","qb8ulwUBRBA",R.drawable.tbb1,57.669138, -165.328862,34.702391, 20.818708,51.707056, -53.676297),
            new Datos_tiburones("Tiburon Tigre","Su presencia es un hecho desde la costa este de Norteamérica hasta la costa este de Brasil, incluyendo el Golfo de México.","La alimentación habitual del tiburón tigre está compuesta por peces, moluscos, crustáceos, tortugas, aves marinas y mamíferos como el dugongo.","DzixvjkBodk", R.drawable.tiger_shark,26.331322, -80.00556,24.160530, -90.814451,-13.377950, -37.775842),
            new Datos_tiburones("Tiburon Ballena","México, Belice, Ecuador, Sudáfrica, Australia y Filipinas.","el menú habitual del enorme tiburón ballena se conforma básicamente de plancton, pero de vez en cuando incluye otros pequeños organismos como el krill y medusas, sardinas, anchoas, caballas, calamares y cangrejos, siempre y cuando tengan un reducido tamaño.","3MDWESW5Jew",R.drawable.tban1,12.212600, 122.797443,17.071106, -88.127653,-34.093479, 28.057425),



    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiburones);
        listtub= (ListView)findViewById(R.id.listtibu);
        listtub.setAdapter(new TiburonAdapter(this,R.layout.list_tiburones,adcDatosFrappe));
        listtub.setOnItemClickListener(this);
        inPlay = new Intent(this,Playmusic.class);
        startService(inPlay);
    }

    @Override
    protected void onPause() {
        super.onPause();
        inPlay = new Intent(this,Playmusic.class);
        stopService(inPlay);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent inDetalles = new Intent(this, Info.class);
        Bundle bnDatos = new Bundle();
        bnDatos.putString("Nom",adcDatosFrappe[position].Nom);
        bnDatos.putString("Habi",adcDatosFrappe[position].Habitat);
        bnDatos.putString("Ali",adcDatosFrappe[position].Alimentacion);
        bnDatos.putString("Video",adcDatosFrappe[position].Video);
        bnDatos.putInt("Img",adcDatosFrappe[position].iImagen);
        bnDatos.putDouble("A1",adcDatosFrappe[position].Alt);
        bnDatos.putDouble("L1",adcDatosFrappe[position].Lng);
        bnDatos.putDouble("A2",adcDatosFrappe[position].Alt2);
        bnDatos.putDouble("L2",adcDatosFrappe[position].Lng2);
        bnDatos.putDouble("A3",adcDatosFrappe[position].Alt3);
        bnDatos.putDouble("L3",adcDatosFrappe[position].Lng3);

        inDetalles.putExtras(bnDatos);
        startActivity(inDetalles);

    }
}

package com.example.eduardo.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {
    Intent inPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        inPlay = new Intent(this,Playmusic.class);
        startService(inPlay);
    }
    public  void blanco(View v){
        Intent acceder= new Intent(this,Blanco.class);
        startActivity(acceder);


    }
    public  void menu(View v){
        Intent acceder= new Intent(this,Tiburones.class);
        startActivity(acceder);


    }
    public void mapa(View v){
        Intent acceder= new Intent(this,MapsActivity.class);
        startActivity(acceder);

    }
}

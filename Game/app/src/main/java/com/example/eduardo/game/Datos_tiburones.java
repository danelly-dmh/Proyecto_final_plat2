package com.example.eduardo.game;

/**
 * Created by eduardo on 27/05/2017.
 */

public class Datos_tiburones {
    String Nom, Habitat, Alimentacion, Video;



    int iImagen;
         double   Alt,Lng, Alt2,Lng2, Alt3,Lng3;
    public Datos_tiburones(String nom, String habitat, String alimentacion,String video, int iImg,double alt, double lng,double alt2, double lng2,double alt3, double lng3) {
        Nom  = nom;
        Habitat = habitat;
        Alimentacion = alimentacion;
        Video = video;

        this.iImagen = iImg;
        this.Alt=alt;
        this.Lng=lng;

        this.Alt2=alt2;
        this.Lng2=lng2;

        this.Alt3=alt3;
        this.Lng3=lng3;
    }


}

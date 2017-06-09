package com.example.eduardo.game;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;

/**
 * Created by eduardo on 27/05/2017.
 */

public class TiburonAdapter extends ArrayAdapter<Datos_tiburones> {
    Context cnContext;
    int iLayout;
    Datos_tiburones[] dcMisDatos;
    public TiburonAdapter(Context context, int resource, Datos_tiburones[] objects) {
        super(context, resource, objects);
        cnContext = context;
        iLayout = resource;
        dcMisDatos = objects;
    }
    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {

        View vwFila = convertView; //fila actual a redibujar

        ImageView imageView2;
        TextView lblNom;
        TextView lblHabitat;
        TextView lblAlimentacion;

        if (vwFila == null) {

            LayoutInflater liMiInflater = ((Activity)cnContext).getLayoutInflater();
            vwFila = liMiInflater.inflate(iLayout,parent,false);

        }

        imageView2 = (ImageView) vwFila.findViewById(R.id.imageView);
        lblNom = (TextView) vwFila.findViewById(R.id.lblNom);
       /* lblHabitat=(TextView)vwFila.findViewById(R.id.lblHabitad);
        lblAlimentacion=(TextView)vwFila.findViewById(R.id.lblAlimentacion);*/



        //Leer los datos
        Datos_tiburones dcDato = dcMisDatos[position];
        imageView2.setImageResource(dcDato.iImagen);
        lblNom.setText(dcDato.Nom);
      /*  lblAlimentacion.setText(dcDato.Alimentacion);
        lblHabitat.setText(dcDato.Habitat);*/


        return vwFila;

    }
}

package com.example.eduardo.game;

import android.app.Dialog;
import android.content.DialogInterface;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class Paint extends AppCompatActivity implements View.OnClickListener {
Button blanco,negro,azul,verde,rojo,trazo,nuevo,guardar,borrar;
    Lienzo lienzo;
    float ppeq,pmed,pgra,pdef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        blanco=(Button)findViewById(R.id.btnblanco);
        negro=(Button)findViewById(R.id.btnnegro);
        azul=(Button)findViewById(R.id.btnazul);
        verde=(Button)findViewById(R.id.btnverde);
        rojo=(Button)findViewById(R.id.bntrojo);
        lienzo=(Lienzo)findViewById(R.id.lienzo);
        trazo=(Button)findViewById(R.id.btntrazo) ;
        nuevo=(Button)findViewById(R.id.btnnuevo) ;
        guardar=(Button)findViewById(R.id.btnguardar) ;
        borrar=(Button)findViewById(R.id.btnborrar) ;




        blanco.setOnClickListener(this);
        negro.setOnClickListener(this);
        verde.setOnClickListener(this);
        azul.setOnClickListener(this);
        rojo.setOnClickListener(this);
        nuevo.setOnClickListener(this);
        trazo.setOnClickListener(this);
        guardar.setOnClickListener(this);
        borrar.setOnClickListener(this);

        ppeq=10;
        pmed=20;
        pgra=30;
        pdef=pmed;





    }


    @Override
    public void onClick(View v) {
        String color=null;

switch (v.getId()){
    case  R.id.btnblanco:
         color= v.getTag().toString();
        lienzo.setColor(color);
        break;
    case  R.id.btnazul:
         color= v.getTag().toString();
        lienzo.setColor(color);
        break;
    case  R.id.bntrojo:
         color= v.getTag().toString();
        lienzo.setColor(color);
        break;
    case  R.id.btnverde:
         color= v.getTag().toString();
        lienzo.setColor(color);
        break;
    case  R.id.btnnegro:
         color= v.getTag().toString();
        lienzo.setColor(color);
        break;
    case  R.id.btntrazo:
      final Dialog btrDialog = new Dialog(this);
        btrDialog.setTitle("selecciona el punto");
        btrDialog.setContentView(R.layout.tam_punto);

        TextView chic= (TextView)btrDialog.findViewById(R.id.lblpeq);
        chic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lienzo.settam(ppeq);
                btrDialog.dismiss();
            }
        });
        TextView med= (TextView)btrDialog.findViewById(R.id.lblmed);
        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lienzo.settam(pmed);
                btrDialog.dismiss();
            }
        });
        TextView grand= (TextView)btrDialog.findViewById(R.id.lblgra);
        grand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lienzo.settam(pgra);
                btrDialog.dismiss();
            }
        });
btrDialog.show();

        break;
    case  R.id.btnnuevo:
        AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
        newDialog.setTitle("Nuevo Dibujo");
        newDialog.setMessage("¿Comenzar nuevo dibujo (perderás el dibujo actual)?");
        newDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){

                lienzo.NuevoDibujo();
                dialog.dismiss();
            }
        });
        newDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        newDialog.show();


        break;
    case  R.id.btnborrar:
        final Dialog borrard = new Dialog(this);
        borrard.setTitle("Tamaño de borrado");
        borrard.setContentView(R.layout.tam_punto);

        TextView borchic= (TextView)borrard.findViewById(R.id.lblpeq);
        borchic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lienzo.setBorrado(true);
                Lienzo.settam(ppeq);
                borrard.dismiss();
            }
        });
        TextView bormed= (TextView)borrard.findViewById(R.id.lblmed);
        bormed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lienzo.setBorrado(true);
                Lienzo.settam(pmed);
                borrard.dismiss();
            }
        });
        TextView borgrand= (TextView)borrard.findViewById(R.id.lblgra);
        borgrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lienzo.setBorrado(true);
                Lienzo.settam(pgra);
                borrard.dismiss();
            }
        });
        borrard.show();
        break;
    case  R.id.btnguardar:
        AlertDialog.Builder salvarDibujo = new AlertDialog.Builder(this);
        salvarDibujo.setTitle("Salvar dibujo");
        salvarDibujo.setMessage("¿Salvar Dibujo a la galeria?");
        salvarDibujo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){

                //Salvar dibujo
                lienzo.setDrawingCacheEnabled(true);
                //attempt to save
                String imgSaved = MediaStore.Images.Media.insertImage(
                        getContentResolver(), lienzo.getDrawingCache(),
                        UUID.randomUUID().toString()+".png", "drawing");
                //Mensaje de todo correcto
                if(imgSaved!=null){
                    Toast savedToast = Toast.makeText(getApplicationContext(),
                            "¡Dibujo salvado en la galeria!", Toast.LENGTH_SHORT);
                    savedToast.show();
                }
                else{
                    Toast unsavedToast = Toast.makeText(getApplicationContext(),
                            "¡Error! La imagen no ha podido ser salvada.", Toast.LENGTH_SHORT);
                    unsavedToast.show();
                }
                lienzo.destroyDrawingCache();


            }
        });
        salvarDibujo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        salvarDibujo.show();
        break;
}
    }

}

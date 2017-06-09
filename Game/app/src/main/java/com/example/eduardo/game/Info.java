package com.example.eduardo.game;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class Info extends AppCompatActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlaybackEventListener ,SensorEventListener {
    YouTubePlayerFragment youtb;
    Intent inPlay;
    String claveyoutube="AIzaSyBGygMwExf6KGFnxTaPfMPV1_59XSoMVvM";
ImageView ImgTib;
    String vid,nom, mens;
    TextView infnom,infhabi,infali;
    double a1,a2,a3,l1,l2,l3;
    SensorManager sensor;
    private Sensor acelerometro, proximidad,luz;
    LinearLayout ln;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        sensor=(SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro= sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proximidad= sensor.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        luz= sensor.getDefaultSensor(Sensor.TYPE_LIGHT);
        ln =(LinearLayout)findViewById(R.id.activity_info);
        ImgTib =(ImageView)findViewById(R.id.imageView3);
        infali=(TextView)findViewById(R.id.infali);
        infnom=(TextView)findViewById(R.id.infnom);
        infhabi=(TextView)findViewById(R.id.infhabi);
        youtb=(YouTubePlayerFragment)getFragmentManager().findFragmentById(R.id.fragment);
        youtb.initialize(claveyoutube,this);
        inPlay = new Intent(this,Playmusic.class);
        startService(inPlay);

        Intent inDatos = getIntent();
        Bundle bnDatos = inDatos.getExtras();

        ImgTib.setImageResource(bnDatos.getInt("Img"));
        infali.setText(bnDatos.getString("Ali"));
        infnom.setText(bnDatos.getString("Nom"));
        infhabi.setText(bnDatos.getString("Habi"));
        vid=bnDatos.getString("Video");
        nom=bnDatos.getString("Nom");
        a1=bnDatos.getDouble("A1");
        l1=bnDatos.getDouble("L1");
        a2=bnDatos.getDouble("A2");
        l2=bnDatos.getDouble("L2");
        a3=bnDatos.getDouble("A3");
        l3=bnDatos.getDouble("L3");
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){
            youTubePlayer.cueVideo(vid);//https://www.youtube.com/watch?v=qb8ulwUBRBA


        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,1).show();
        }else{
            String error="Error al iniacilizar youtube"+ youTubeInitializationResult.toString();
            Toast.makeText(getApplication(),error,Toast.LENGTH_SHORT).show();
        }
    }
    protected void onActivityResult(int requestcode, int resultcode, Intent data){
        if(requestcode==1){
            getYoutubePlayerProvider().initialize(claveyoutube,this );
        }

    }
    protected YouTubePlayer.Provider getYoutubePlayerProvider(){
        return  youtb;
    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }
    public  void mapa(View v){
        Intent inDetalles = new Intent(this, MapsActivity.class);
        Bundle bnDatos = new Bundle();
        bnDatos.putString("nom",nom);
        bnDatos.putDouble("a1",a1);
        bnDatos.putDouble("l1",l1);
        bnDatos.putDouble("a2",a2);
        bnDatos.putDouble("l2",l2);
        bnDatos.putDouble("a3",a3);
        bnDatos.putDouble("l3",l3);
        inDetalles.putExtras(bnDatos);
        startActivity(inDetalles);

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
            if(x<100){
                ln.setBackgroundColor(Color.CYAN);
            }else if(x<200){
                ln.setBackgroundColor(Color.GREEN);

            }else if(x<300){
                ln.setBackgroundColor(Color.MAGENTA);
            }else if(x<400){
                ln.setBackgroundColor(Color.RED);
            }

        }

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void comprar(View v){
        Intent intent = new Intent(this, Paint.class);
        startActivity(intent);
    }
    public void compartir (View v){
        mens = "mi hijo acaba de aprender sobre"+ nom;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "algo");
        intent.setPackage("com.facebook.katana");
        startActivity(Intent.createChooser( intent,"share with"));
    }
}

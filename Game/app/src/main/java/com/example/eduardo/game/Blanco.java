package com.example.eduardo.game;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class Blanco extends AppCompatActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlaybackEventListener {
    YouTubePlayerFragment youtb;
    String claveyoutube="AIzaSyBGygMwExf6KGFnxTaPfMPV1_59XSoMVvM";
    String vid="qb8ulwUBRBA";
    ImageView img;
    int i;
    Handler hand = new Handler();
    Runnable rnFioreground = new Runnable() {
        @Override
        public void run() {
            switch (i){
                case 1:
                    img.setImageResource(R.drawable.tbb1);



                    break;
                case 2:
                    img.setImageResource(R.drawable.tbb2);



                    break;
                case 3:
                    img.setImageResource(R.drawable.tbb3);


                    break;


            }
            if(i <3){
                i++;

            }else{
                i=1;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blanco);
        youtb=(YouTubePlayerFragment)getFragmentManager().findFragmentById(R.id.fragment);
        youtb.initialize(claveyoutube,this);
        img = (ImageView)findViewById(R.id.imgtibblanc);
        Runnable Rnbackgroun = new Runnable() {
            @Override
            public void run() {
                while (true){
                    hand.post(rnFioreground);
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }

            }
        };
        Thread start = new Thread(Rnbackgroun);
        start.start();
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
}

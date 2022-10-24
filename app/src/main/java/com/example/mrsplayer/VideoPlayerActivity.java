package com.example.mrsplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoPlayerActivity extends AppCompatActivity {



    boolean isopen=true;
    private TextView videoTName,videoTime,timerun;
    private ImageButton back,play,forward;
    private SeekBar videoSeekBar;
    private VideoView videoView;
    private  String videoName,videPath;
    private RelativeLayout controlURL,videoRL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);


        videoName=getIntent().getStringExtra("VideoName");
        videPath=getIntent().getStringExtra("videoPath");

        videoTName=findViewById(R.id.idTVVideoTitle);
        videoTime=findViewById(R.id.idTVTime);
        back=findViewById(R.id.idback);
        play=findViewById(R.id.idplay);
        forward=findViewById(R.id.idforward);
        videoSeekBar=findViewById(R.id.idSeekbarProgress);
        videoView=findViewById(R.id.idVideoView);
        controlURL=findViewById(R.id.idRLControls);
        videoRL=findViewById(R.id.idRLVideo);
        timerun=findViewById(R.id.idRunningTimeID);


        videoView.setVideoURI(Uri.parse(videPath));


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoSeekBar.setMax(videoView.getDuration());
                videoView.start();
                hideControls();
            }
        });

        videoTName.setText(videoName);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.seekTo(videoView.getCurrentPosition()-10000);
                hideControls();
            }
        });

     play.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(videoView.isPlaying()){
                 videoView.pause();
                 play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play));
                 hideControls();

             }else
             {
                 videoView.start();
                 play.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause));
                 hideControls();
             }


         }
     });

     forward.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             videoView.seekTo(videoView.getCurrentPosition()+10000);
             hideControls();
         }

     });



     videoRL.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             
             if(isopen){
                 hideControls();
                isopen=false;
             }else
             {
                 showControls();
                 isopen=true;
             }

         }
     });

     setHandler();
     initializeSeekBar();

    }

    private void setHandler() {
        Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                if(videoView.getDuration() >0){
                    int cusPos=videoView.getCurrentPosition();
                    videoSeekBar.setProgress(cusPos);
                    videoTime.setText(""+convertTime(videoView.getDuration()));
                    timerun.setText(""+convertTime(videoView.getCurrentPosition()));



                }

                handler.postDelayed(this,0);
            }
        };

        handler.postDelayed(runnable,500);
    }

    private void initializeSeekBar() {

        videoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(videoSeekBar.getId()== R.id.idSeekbarProgress){

                    if(fromUser){
                        videoView.seekTo(progress);
                        videoView.start();
                        int Curspos=videoView.getCurrentPosition();
                        videoTime.setText(""+convertTime(videoView.getDuration()));
                        timerun.setText(""+convertTime(videoView.getCurrentPosition()));

                    }

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
    private String convertTime(int ms) {

        String time;
        int x,seconds,minutes,hours;
        x=ms/1000;
        seconds=x%60;
        x/=60;
        minutes=x%60;
        x/=60;

        hours=x%24;
        if(hours!=0){

            time=String.format("%02d",hours)+":"+String.format("%02d",minutes)+":"+String.format("%02d",seconds);

        }else
        {
            time=String.format("%02d",minutes)+":"+String.format("%02d",seconds);
        }

        return time;
    }

    private void showControls() {
        controlURL.setVisibility(View.VISIBLE);

        final Window window=this.getWindow();
        if(window==null)
        {
            return;
        }

       window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        View decorView=window.getDecorView();

        if(decorView!=null){
            int uiOption =decorView.getSystemUiVisibility();

            if(Build.VERSION.SDK_INT>=14){
                uiOption &=~View.SYSTEM_UI_FLAG_LOW_PROFILE;
            }
            if(Build.VERSION.SDK_INT>=16){
                uiOption &=~View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            }
            if(Build.VERSION.SDK_INT>=19){
                uiOption &=~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }

          decorView.setSystemUiVisibility(uiOption);



        }


    }

    private void hideControls() {
        controlURL.setVisibility(View.GONE);

        final Window window=this.getWindow();
        if(window==null)
        {
            return;
        }

        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        View decorView=window.getDecorView();

        if(decorView!=null){
            int uiOption =decorView.getSystemUiVisibility();

            if(Build.VERSION.SDK_INT>=14){
                uiOption &=~View.SYSTEM_UI_FLAG_LOW_PROFILE;
            }
            if(Build.VERSION.SDK_INT>=16){
                uiOption &=~View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            }
            if(Build.VERSION.SDK_INT>=19){
                uiOption &=~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }

            decorView.setSystemUiVisibility(uiOption);

        }

    }
}
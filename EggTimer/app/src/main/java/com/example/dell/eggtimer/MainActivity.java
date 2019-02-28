package com.example.dell.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     public TextView timerTextView;
    public SeekBar timerseekbar;
    public Boolean counterisactive=false;
    public Button controllerButton;
    public CountDownTimer countDownTimer;
    public void updateTimer(int secondsleft){

        int minutes=(int) secondsleft/60; //no of seconds in total divided by 60.
        int seconds=secondsleft - minutes*60; //total number of seconds - number of seconds already included.
        //which gives us the number of seconds left.
        String secondstring=Integer.toString(seconds);
       if(seconds<=9){
            secondstring = "0" + secondstring;
        }
        final TextView timerTextView=findViewById(R.id.timerTextView);
         timerTextView.setText(Integer.toString(minutes)+ ":" + secondstring);
    }

    public void controltimer(View view){

        if(counterisactive==false) {


            counterisactive = true;
            timerseekbar.setEnabled(false);
            Button controllerButton=findViewById(R.id.controllerButton);
            controllerButton.setText("STOP");
            Log.i("button clicked", "pressed");
            countDownTimer =new CountDownTimer(timerseekbar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);

                }

                @Override
                public void onFinish() {
                    Log.i("finished", "timer done!");
                    final TextView timerTextView = findViewById(R.id.timerTextView);
                    timerTextView.setText("0:00");
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                    mplayer.start();


                }//yeh saare miliseconds mei hota hai

            }.start();
        }
        else{
            TextView timerTextView=findViewById(R.id.timerTextView);
            Button controllerButton=findViewById(R.id.controllerButton);
            timerseekbar.setProgress(30);
            timerTextView.setText("0:30");
            countDownTimer.cancel();
            controllerButton.setText("GO!");
            timerseekbar.setEnabled(true);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        controllerButton=findViewById(R.id.controllerButton);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerseekbar=findViewById(R.id.TimerSeekBar);
        final TextView timerTextView=findViewById(R.id.timerTextView);
        timerseekbar.setMax(600);
        timerseekbar.setProgress(30);
        timerseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

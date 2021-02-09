package com.example.fom;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

import java.io.Console;

public class RecordPlayQ1 extends AppCompatActivity {
    private static int VIDEO_REQUEST = 101;
    private Uri videoUri = null;
    private static int videoCounter=1;
    private Button saveB,playB;
    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_play_q1);
        Button myButton;

        myButton = (Button) findViewById(R.id.buttonSaveVid);
        saveB=findViewById(R.id.buttonSaveVid);
        playB=findViewById(R.id.play);
        myButton.setOnClickListener(new View.OnClickListener() {

            int counter = 1;

            @Override
            public void onClick(View view) {
                //System.out.println(counter);
               // Bundle extras = getIntent().getExtras();
//                Intent intent = new Intent(getApplicationContext(), RecordPlayQ1.class);
               // if(extras != null)
                //{
                    //int passCount = extras.getInt("NEXT_STEP");
                    int passCount=Data.getInstance().step;
                    if (passCount == 1) {
                        videoCounter++;
                        Intent intentNext = new Intent(getApplicationContext(), ScdQ.class);
                        startActivity(intentNext);
                    } else if (passCount == 2) {
                        videoCounter++;
                        Intent intentNext = new Intent(getApplicationContext(), ThirdQ.class);
                        startActivity(intentNext);
                    } else {
                        Intent intentNext = new Intent(getApplicationContext(), DoneQ.class);
                        startActivity(intentNext);
                    }

                }

            //}
        });

    }


    public void captureVideo(View view) {
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        videoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 60);
        //videoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,0);
        videoIntent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if (videoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(videoIntent, VIDEO_REQUEST);
        }
    }

    public void playVideo(View view) {
        Intent playIntent = new Intent(this, VideoPlayActivity.class);
        playIntent.putExtra("videoUri", videoUri.toString());
        startActivity(playIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK) {
            saveB.setVisibility(View.VISIBLE);
            playB.setVisibility(View.VISIBLE);
            //System.out.println(data.getData().toString());
            videoUri = data.getData();
            switch (videoCounter){
                case 1:
                    Data.getInstance().path1 = data.getData().toString();
                    break;
                case 2:
                    Data.getInstance().path2 = data.getData().toString();
                    break;
                case 3:
                    Data.getInstance().path3 = data.getData().toString();
                    break;
            }
           // Data.getInstance().path1 = data.getData().toString();
            //System.out.println(Data.getInstance().path1);
        }
    }
}

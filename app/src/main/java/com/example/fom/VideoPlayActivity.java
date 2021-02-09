package com.example.fom;

//import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayActivity extends AppCompatActivity {

    private VideoView myVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        myVideoView = findViewById(R.id.videoView);
        Uri videoUri =  Uri.parse(getIntent().getExtras().getString("videoUri"));
        myVideoView.setVideoURI(videoUri);
        myVideoView.start();
    }
}
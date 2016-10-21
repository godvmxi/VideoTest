package com.example.dan.videotest;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.io.File;



public class MainActivity extends AppCompatActivity {
    Button buttonList;
    Button buttonNext;
    Button buttonPlay;
    String TAG;

    int fileIndex;
    TextView textViewCurFile;
    TextView textViewFileList;
    File[]  fileList;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG =  new String("VideoTest");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonList = (Button)findViewById(R.id.buttonList);
        buttonPlay = (Button)findViewById(R.id.buttonPlay);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        textViewCurFile = (TextView)findViewById(R.id.textViewCurFile);
        textViewFileList = (TextView)findViewById(R.id.textViewFileList);
        videoView = (VideoView)findViewById(R.id.videoView);


        fileIndex = 0;
        fileList  = new File[]{};

        textViewCurFile.setText("Current file -> NULL");
        textViewFileList.setText("File list -> ");



        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: ");
                File fileDir = new File("/mnt/sdcard/Movies");
                Log.d(TAG, "onClick: read ->"+ fileDir.canRead());
                Log.d(TAG, "onClick: write ->"+ fileDir.canWrite());
                fileList = fileDir.listFiles();
                String fileListString = new String("File list -> ");
                for(int i = 0 ;i < fileList.length;i++){
                    Log.d(TAG, "file list -> : " + fileList[i].toString());
                    fileListString = fileListString + "\n" + fileList[i].toString();
                }
                fileIndex = 0;
                textViewFileList.setText(fileListString);
                textViewCurFile.setText(fileList.length+":"+ fileIndex+"->" + fileList[fileIndex].toString());
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //videoView.stopPlayback();
                String curFile = fileList[fileIndex].toString();
                videoView.setVideoURI(Uri.parse(curFile));
                videoView.start();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fileList.length <= 0 ){
                    Toast.makeText(getApplicationContext(), "Click the list first",500).show();
                    textViewCurFile.setText(fileList.length+":"+ (fileIndex+1)+"-> NULL");
                }
                else {
                    fileIndex++;
                    if(fileIndex >= fileList.length ){
                        Toast.makeText(getApplicationContext(), "back to first file",500).show();
                        fileIndex = 0;
                    }
                    textViewCurFile.setText(fileList.length+":"+ (fileIndex+1) +"->" + fileList[fileIndex].toString());
                }
            }
        });
    }
}
